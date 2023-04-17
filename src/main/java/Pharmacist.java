import java.util.Objects;

/**
 * The Pharmacist class represents an employee who is a pharmacist, with a
 * state license ID.
 */
public class Pharmacist extends Employee {
    private String stateLicenseId;

    public Pharmacist(String name, String phoneNumber, Address address,
                      String employeeID, String stateLicenseId) {
        super(name, phoneNumber, address, employeeID);
        this.stateLicenseId = stateLicenseId;
    }

    public String getStateLicenseId() {
        return stateLicenseId;
    }

    public void setStateLicenseId(String stateLicenseId) {
        this.stateLicenseId = stateLicenseId;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "stateLicenseId='" + stateLicenseId + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pharmacist)) return false;
        if (!super.equals(o)) return false;
        Pharmacist that = (Pharmacist) o;
        return (getStateLicenseId().equals(that.getStateLicenseId())
                && getName().equals(that.getName())
                && getEmployeeID().equals(that.getEmployeeID()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStateLicenseId());
    }
}
