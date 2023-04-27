package person;

import misc.Address;
import java.util.Objects;
import misc.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Person.PharmacyTechnician class represents an employee who is a pharmacy technician, with a state
 * license ID and cashier training.
 */
public class PharmacyTechnician extends Employee {
    private static final Logger LOG = LogManager.getLogger(PharmacyTechnician.class);

    private String stateLicenseId;
    private boolean isCashierTrained;

    public PharmacyTechnician(String name, String phoneNumber, Address address,
        String stateLicenseId) {
        super(name, phoneNumber, address);
        this.stateLicenseId = stateLicenseId;
        this.isCashierTrained = false;

        LOG.trace("Pharmacist created with eID: " + super.employeeId);
    }

    public String getStateLicenseId() {
        return stateLicenseId;
    }

    public void setStateLicenseId(String stateLicenseId) {
        this.stateLicenseId = stateLicenseId;
    }

    public boolean isCashierTrained() {
        return isCashierTrained;
    }

    public void setCashierTrained(boolean cashierTrained) {
        isCashierTrained = cashierTrained;
    }

    /**
     * method to print out employee details
     */
    @Override
    public void printEmployeeDetails() {
        super.printDetails();
        System.out.println("Position: Pharmacy.Pharmacy Technician");
        System.out.println("EmployeeId: " + super.employeeId);
        System.out.println("LicenseId: " + this.stateLicenseId);
        System.out.println("Cashier Trained: " + this.isCashierTrained);

    }


    @Override
    public String toString() {
        return "Person.PharmacyTechnician{" +
            "stateLicenseId='" + stateLicenseId + '\'' +
            ", isCashierTrained=" + isCashierTrained +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PharmacyTechnician)) {
            return false;
        }
        PharmacyTechnician that = (PharmacyTechnician) o;
        return isCashierTrained == that.isCashierTrained &&
            Objects.equals(getStateLicenseId(), that.getStateLicenseId()) &&
            Objects.equals(getEmployeeID(), that.getEmployeeID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStateLicenseId(), isCashierTrained());
    }
}
