/**
 * Interface for representing a customer who can provide a prescription and interact with a shopping
 * cart.
 */
public interface ICustomer {

    //TODO: For now, we consider this method to belong to the customer where the pharmacy is
    // forced to accept the customer's prescription. Other considerations include a pharmacy-patient
    // object or interface implementation since a patient can only offer their prescription and its
    // on the pharmacy side to accept the prescription and register to their database
    /**
     * Provides a prescription for the given prescription, which can be used to purchase
     * medication.
     *
     * @param pharmacy     the pharmacy to provide the prescription to
     * @param prescription the prescription to provide
     */
    void providePrescription(Pharmacy pharmacy, Prescription prescription);

    /**
     * Adds the given quantity of the given product to the specified cart.
     *
     * @param cart     the cart to add the product to
     * @param product  the product to add
     * @param quantity the quantity of the product to add
     */
    void addToCart(Cart cart, Product product, int quantity);

    /**
     * Removes the given quantity of the given product from the specified cart.
     *
     * @param cart     the cart to remove the product from
     * @param product  the product to remove
     * @param quantity the quantity of the product to remove
     */
    void removeFromCart(Cart cart, Product product, int quantity);
}