import java.util.ArrayList;

/**
 * The Inventory class represents the inventory of a pharmacy, which consists
 * of products and medications.
 */
public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Medication> medications;

    public Inventory(ArrayList<Product> products,
                     ArrayList<Medication> medications) {
        this.products = products;
        this.medications = medications;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                ", medications=" + medications +
                '}';
    }
}