package register;

import static setup.AppConfig.SHOW_RX_STATUS_FLOW;

import customLambdaFunctions.INullChecker;
import enums.PaymentType;
import enums.PrescriptionStatus;
import exceptions.InsufficientQuantityException;
import exceptions.InvalidPrescriptionException;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductOutOfStockException;
import inventory.Cart;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import misc.Insurance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Employee;
import person.Patient;
import prescriptionRegistry.Prescription;
import prescriptionRegistry.PrescriptionFilledLog;
import product.Medication;
import product.Product;

public class Register implements IRegister {

    private static final Logger LOG = LogManager.getLogger(Register.class);
    private static AtomicInteger transactionId = new AtomicInteger(0);
    private Employee employee;
    private AbstractCustomer abstractCustomer;
    private Cart cart;
    private PaymentType paymentType;
    private List<Prescription> patientPrescriptions;

    private List<Product> scannedProducts;
    private boolean transactionCompleted;
    private boolean txnProcessesPrescription;

    public Register(Employee employee) {
        this.employee = employee;
        this.scannedProducts = new ArrayList<>();
        this.transactionCompleted = false;
        this.paymentType = PaymentType.NOT_SELECTED;
        this.cart = new Cart();
        this.txnProcessesPrescription = false;
        this.patientPrescriptions = null;
    }

    public void setPatientPrescriptions(List<Prescription> patientPrescriptions) {
        this.patientPrescriptions = patientPrescriptions;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Boolean getTransactionCompleted() {
        return this.transactionCompleted;
    }

    // TODO: Customer or ICashier can select payment type
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<Product> getScannedProducts() {
        return scannedProducts;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AbstractCustomer getCustomer() {
        return abstractCustomer;
    }

    public void setCustomer(AbstractCustomer abstractCustomer) {
        this.abstractCustomer = abstractCustomer;

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean isTransactionCompleted() {
        return transactionCompleted;
    }

    INullChecker<AbstractCustomer> abstractCustomerNullChecker = Objects::isNull;

    @Override
    public double getTotal() {
        double total = scannedProducts.stream()
            .map(p -> calculatePrice(p.getPrice()))
            .reduce(0.0, Double::sum);
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(total));
    }

    private double calculatePrice(double originalPrice) {
        if (abstractCustomer.isPatient()) {
            return calculateDiscountPrice(originalPrice);
        } else {
            return originalPrice;
        }
    }

    private double calculateDiscountPrice(double originalPrice) {
        Function<AbstractCustomer, Patient> abstractCustomerToPatient = p -> (Patient) p;
        Insurance patientInsurance = abstractCustomerToPatient.apply(abstractCustomer)
            .getInsurance();
        double insuranceDiscount = patientInsurance.getPercentInsuranceCovered();
        double discountedPrice = originalPrice * (1 - insuranceDiscount / 100);
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(discountedPrice));

    }

    public void scanAllProductsInCart() {
        if (abstractCustomerNullChecker.isNull(abstractCustomer)) {
            LOG.warn("AbstractCustomer is not set");
        }
        List<Product> productsToScan = cart.getProducts().entrySet().stream()
            .flatMap(entry -> Collections.nCopies(entry.getValue(), entry.getKey()).stream())
            .collect(Collectors.toList());

        productsToScan.forEach(this::scanProduct);
    }

    private boolean isPrescriptionFilledForPatient(PrescriptionFilledLog prescriptionFilledLog) {
        try {
            List<Prescription> filledPrescriptionsByPatient = prescriptionFilledLog.getFilledPrescriptionsByPatient(
                (Patient) abstractCustomer);
            return !filledPrescriptionsByPatient.isEmpty();
        } catch (InvalidPrescriptionException e) {
            LOG.trace(e.getMessage());
            return false;
        }
    }

    public void processPrescriptionAndAddMedicationsToCart(
        PrescriptionFilledLog prescriptionFilledLog) {
        if (abstractCustomer.isPatient() && isPrescriptionFilledForPatient(prescriptionFilledLog)) {
            if (SHOW_RX_STATUS_FLOW) {
                LOG.info(abstractCustomer.getName() + " is at register with cashier "
                    + employee.getName() + " for Rx pickup.");
            }
            addRequestedMedicationsToCart(prescriptionFilledLog);
            txnProcessesPrescription = true;
        }
    }

    // TODO: Better solution would be to implement a notification system using the observer pattern
    //  where the register notifies the employee that the Rx is filled. Then the employee action
    //  to retrieve the medication would be separated to the employee class
    public void addRequestedMedicationsToCart(PrescriptionFilledLog prescriptionFilledLog) {
        if (!abstractCustomer.isPatient()) {
            LOG.error("Customer is not a patient.");
        }
        try {
            LOG.trace("Employee: " + employee.getEmployeeID() + " added medications to cart");
            List<Prescription> patientPrescriptions = prescriptionFilledLog.getFilledPrescriptionsByPatient(
                (Patient) abstractCustomer);
            setPatientPrescriptions(patientPrescriptions);
            for (Prescription p : patientPrescriptions) {
                List<Medication> patientPrescribedMedications = prescriptionFilledLog.getMedicationsByPrescription(
                    p);
                if (p.getPrescriptionStatus() == PrescriptionStatus.COMPLETED) {
                    return;
                } else {
                    // TODO: idxOutOfBounds sometimes occur
                    try {
                        cart.addProduct(patientPrescribedMedications.get(0),
                            patientPrescribedMedications.size());
                        if (SHOW_RX_STATUS_FLOW) {
                            LOG.info("Cashier " + employee.getName() + " added " + p.getMedication()
                                .getName()
                                + " to " + p.getPatient().getName() + "'s cart");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        LOG.error(patientPrescribedMedications.toString());
                    }

                }
                //TODO: removeFilledPrescription assumes that every patient will complete transaction. This can be handled
                // in a new method that handles unprocessed prescriptionFilledLog
                prescriptionFilledLog.removeFilledPrescription(p);
            }
        } catch (InvalidPrescriptionException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void scanProduct(Product product) {
        try {
            if (cart.getQuantity(product) == 0) {
                cart.remove(product);
            } else {
                cart.removeProduct(product, 1);
            }
        } catch (InsufficientQuantityException | ProductDoesNotExistException | ProductOutOfStockException e) {
            LOG.debug(product.getName());
            LOG.warn(e.getMessage() + e.getClass());
        }
        scannedProducts.add(product);
        LOG.trace(
            "Scanned " + product.getName() + " for Non-discounted price of $" + product.getPrice());
    }

    @Override
    public void processTransaction() {
        if (abstractCustomerNullChecker.isNull(abstractCustomer)) {
            LOG.warn("There is no customer for the transaction");
            return;
        }
        double customerBalance = abstractCustomer.getCreditBalance();
        double transactionTotal = this.getTotal();
        LOG.trace(
            abstractCustomer.getName() + " purchased $" + transactionTotal + " worth of products.");
        if (customerBalance < transactionTotal) {
            LOG.warn("Payment Declined: Insufficient funds available");
            LOG.debug("AbstractCustomer has " + customerBalance + ". Total is " + transactionTotal);
            return;
        }
        double newCustomerBalance = customerBalance - transactionTotal;
        abstractCustomer.setCreditBalance(newCustomerBalance);
        this.transactionCompleted = true;
    }

    public String generateReceiptString() {
        if (!this.transactionCompleted) {
            LOG.warn("Cannot generate receipt before abstractCustomer payment");
        }
        StringBuilder sb = new StringBuilder();
        String txnIdLine = "TransactionId: " + "txn-" + transactionId.incrementAndGet() + System.lineSeparator();
        String cashierInfo = "ICashier: " + employee.getName() + System.lineSeparator() + "Id: "
            + employee.getEmployeeID() + System.lineSeparator();
        sb.append(txnIdLine);
        sb.append(cashierInfo);
        Map<Product, Long> occurrences = scannedProducts.stream()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        for (Map.Entry<Product, Long> entry : occurrences.entrySet()) {
            sb.append(entry.getKey().getName()).append("    x")
                .append(entry.getValue().intValue() + System.lineSeparator());
        }

        String totalLine = "Total: " + this.getTotal() + System.lineSeparator();
        sb.append(totalLine);
        return sb.toString();
    }

    @Override
    public void logReceipt() {
        LOG.trace(this.generateReceiptString());
    }

    public Receipt printReceipt() {
        Receipt receipt = new Receipt(this.generateReceiptString());
        if (txnProcessesPrescription) {
            for (Prescription p : patientPrescriptions) {
                p.setNumRefills(p.getNumRefills() - 1);
                if (p.getNumRefills() == 0) {
                    p.setPrescriptionStatus(PrescriptionStatus.COMPLETED);
                } else {
                    p.setPrescriptionStatus(PrescriptionStatus.REFILL_UPON_REQUEST);
                }
                if (SHOW_RX_STATUS_FLOW) {
                    LOG.warn(
                        "Cashier " + employee.getName() + " changed the rx for " + p.getMedication()
                            .getName() + " for patient " + p.getPatient().getName() + " status to "
                            + p.getPrescriptionStatus());
                }
            }
        }
        LOG.trace("Receipt printed for customer: " + abstractCustomer.getName());
        this.reset();
        return receipt;
    }

    private void reset() {
        this.abstractCustomer = null;
        this.scannedProducts.clear();
        this.transactionCompleted = false;
        this.txnProcessesPrescription = false;
        setPatientPrescriptions(null);
        setPaymentType(PaymentType.NOT_SELECTED);
    }
}
