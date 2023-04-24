public abstract class Customer extends Person implements ICustomer {

    public Customer(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
    }

    /**
     * Adds the given quantity of the given product to the specified cart.
     *
     * @param cart     the cart to add the product to
     * @param product  the product to add
     * @param quantity the quantity of the product to add
     */
    @Override
    public void addToCart(Cart cart, Product product, int quantity) {
        cart.addProduct(product,quantity);
    }

    /**
     * Removes the given quantity of the given product from the specified cart.
     *
     * @param cart     the cart to remove the product from
     * @param product  the product to remove
     * @param quantity the quantity of the product to remove
     */
    @Override
    public void removeFromCart(Cart cart, Product product, int quantity) {
        cart.removeProduct(product,quantity);
    }
}