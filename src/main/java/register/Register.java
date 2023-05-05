package register;

import exceptions.InsufficientQuantityException;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductOutOfStockException;
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

    public boolean isTransactionCompleted() {
        return transactionCompleted;
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

    public void scanAllProductsInCart() {
        if (customer == null) {
            LOG.warn("Customer is not set");
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
        try {
            cart.removeProduct(product, 1);
        } catch (InsufficientQuantityException | ProductDoesNotExistException | ProductOutOfStockException e) {
            LOG.warn(e.getMessage());
        }
        scannedProducts.add(product);
        LOG.info("Scanned " + product.getName() + " for $" + product.getPrice());
    }

    @Override
    public void processTransaction() {
        if (customer == null) {
            LOG.warn("Customer is not set");
        }
        if (customer.getCreditBalance() < this.getTotal()) {
            LOG.warn("Payment Declined: Insufficient funds available");
            LOG.debug(
                "Customer has " + customer.getCreditBalance() + ". Total is " + this.getTotal());
        }

        double newCustomerBalance = customer.getCreditBalance() - this.getTotal();
        customer.setCreditBalance(newCustomerBalance);
        this.transactionCompleted = true;
        transactionId += 1;

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
