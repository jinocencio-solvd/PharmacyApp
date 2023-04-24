/**
 * The ICustomer interface represents a customer who interacts with a pharmacy store and their
 * cart.
 */
public interface ICustomer {

    void providePrescription(Prescription prescription, Pharmacy pharmacy);

    void addToCart(Cart cart);

    void removeFromCart();
}
