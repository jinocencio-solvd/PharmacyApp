package register;

import exceptions.AbsentCustomerException;
import exceptions.InsufficientQuantityException;
import inventory.Cart;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import misc.Insurance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Customer;
import person.Employee;
import person.Patient;
import product.Product;

public class Register implements IRegister {

    private static final Logger LOG = LogManager.getLogger(Register.class);
    private static String transactionId;
    private Employee employee;
    private Customer customer;
    private Cart cart;

    private List<Product> scannedProducts;
    private boolean transactionCompleted;

    public Register(Employee employee) {
        this.employee = employee;
        this.scannedProducts = new ArrayList<>();
        this.transactionCompleted = false;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Product p : scannedProducts) {
            double price = calculatePrice(p.getPrice());
            total += price;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(total));
    }

    private double calculatePrice(double originalPrice) {
        if (customer instanceof Patient) {
            Insurance patientInsurance = ((Patient) customer).getInsurance();
            double insuranceDiscount = patientInsurance.getPercentInsuranceCovered();
            return originalPrice * (1 - insuranceDiscount / 100);
        } else {
            return originalPrice;
        }
    }

    public void scanAllProductsInCart() throws AbsentCustomerException {
        if (customer == null) {
            throw new AbsentCustomerException("Customer is not set for checkout");
        }
        for (Product p : cart.getProducts().keySet()) {
            int productQty = cart.getProducts().get(p);
            for (int i = 0; i < productQty; i++) {
                scanProduct(p);
            }
        }
    }

    @Override
    public void scanProduct(Product product) {
        scannedProducts.add(product);
        LOG.info("Scanned " + product.getName() + " for $" + product.getPrice());
    }

    @Override
    public void processTransaction() {
        try {
            if (customer == null) {
                throw new AbsentCustomerException("Customer is not set for checkout");
            }
            if (customer.getCreditBalance() < this.getTotal()) {
                throw new InsufficientQuantityException("Customer does not have enough funds");
            }
            double newCustomerBalance = customer.getCreditBalance() - this.getTotal();
            customer.setCreditBalance(newCustomerBalance);
            this.transactionCompleted = true;
            transactionId += 1;
        } catch (InsufficientQuantityException e) {
            LOG.warn("Payment Declined: Insufficient funds available");
        } catch (AbsentCustomerException e) {
            LOG.warn("Customer is not set");
        }
    }

    public String generateReceiptString() {
        if (!this.transactionCompleted) {
            LOG.warn("Cannot generate receipt before customer payment");
        }
        StringBuilder sb = new StringBuilder();
        String txnIdLine = "TransactionId: " + "txn-" + transactionId + System.lineSeparator();
        sb.append(txnIdLine);
        for (Product p : scannedProducts) {
            String itemLine = p.getName() + "   " + p.getPrice() + System.lineSeparator();
            sb.append(itemLine);

        }
        String totalLine = "Total: " + this.getTotal();
        sb.append(totalLine);
        return sb.toString();
    }

    @Override
    public void logReceipt() {
        LOG.info(this.generateReceiptString());
    }

    public Receipt printReceipt() {
        return new Receipt(this.generateReceiptString());
    }

    private void reset() {
        this.customer = null;
        this.scannedProducts.clear();
        this.transactionCompleted = false;
    }
}
