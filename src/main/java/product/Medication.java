package product;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Product.Medication class represents a medication with a name, dosage, price, and quantity.
 */
public class Medication extends Product {

    private static final Logger LOG = LogManager.getLogger(Medication.class);
    private String dosage;

    public Medication(String name, String dosage, double price) {
        super(name, price);
        this.dosage = dosage;
        this.productType = ProductType.PRESCRIBED_MEDICINE;

        LOG.trace("Medication created: " + this.name);
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Product.Medication{" +
            "dosage='" + dosage + '\'' +
            ", name='" + name + '\'' +
            ", price=" + price +
            "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Medication)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Medication that = (Medication) o;
        return Objects.equals(getDosage(), that.getDosage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDosage());
    }
}
