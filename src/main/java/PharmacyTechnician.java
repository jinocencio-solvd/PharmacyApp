import java.util.Objects;

/**
 * The PharmacyTechnician class represents an employee who is a pharmacy technician, with a state
 * license ID and cashier training.
 */
public class PharmacyTechnician extends Employee {

  private String stateLicenseId;
  private boolean isCashierTrained;

  public PharmacyTechnician(String name, String phoneNumber, Address address, String employeeID,
      String stateLicenseId) {
    super(name, phoneNumber, address, employeeID);
    this.stateLicenseId = stateLicenseId;
    this.isCashierTrained = false;
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
    System.out.println("Position: Pharmacy Technician");
    System.out.println("EmployeeId: " + super.employeeId);
    System.out.println("LicenseId: " + this.stateLicenseId);
    System.out.println("Cashier Trained: " + this.isCashierTrained);

  }


  @Override
  public String toString() {
    return "PharmacyTechnician{" +
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
    if (!super.equals(o)) {
      return false;
    }
    PharmacyTechnician that = (PharmacyTechnician) o;
    return isCashierTrained() == that.isCashierTrained() &&
        Objects.equals(getStateLicenseId(), that.getStateLicenseId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getStateLicenseId(),
        isCashierTrained());
  }
}
