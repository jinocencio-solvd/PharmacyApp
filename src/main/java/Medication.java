/**
 * The Medication class represents a medication with a name, dosage, price,
 * and quantity.
 */
public class Medication extends Product {
    private String dosage;

    public Medication(String name, String dosage, double price, int quantity) {
        super(name, price, quantity);
        this.dosage = dosage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
