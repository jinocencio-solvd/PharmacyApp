package person;

import exceptions.InsufficientQuantityException;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductOutOfStockException;
import inventory.Cart;
import misc.Address;
import product.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Customer extends Person implements ICustomer {

    private static final Logger LOG = LogManager.getLogger(Pharmacist.class);
    private double creditBalance;

    public Customer(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
        this.creditBalance = 0;
    }

    public Customer(String name, String phoneNumber, Address address, double creditBalance) {
        super(name, phoneNumber, address);
        this.creditBalance = creditBalance;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    /**
     * Adds the given quantity of the given product to the specified cart.
     *
     * @param cart     the cart to add the product to
     * @param product  the product to add
     * @param quantity the quantity of the product to add
     */
    @Override
    public final void addToCart(Cart cart, Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    /**
     * Removes the given quantity of the given product from the specified cart.
     *
     * @param cart     the cart to remove the product from
     * @param product  the product to remove
     * @param quantity the quantity of the product to remove
     */
    @Override
    public final void removeFromCart(Cart cart, Product product, int quantity) {
        try {
            cart.removeProduct(product, quantity);
        } catch (InsufficientQuantityException e) {
            int productQuantity = cart.getQuantity(product);
            LOG.warn("Unable to retrieve product of +" + quantity
                + " from the cart. Current quantity of " + product.getName() + " in cart is "
                + productQuantity + ".");
        } catch (ProductDoesNotExistException e) {
            LOG.warn("Product item " + product.getName() + "does not exist in cart.");
        } catch (ProductOutOfStockException e) {
            LOG.warn("Product item " + product.getName() + "is not in your cart.");
        }
    }
}