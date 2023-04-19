import java.util.Objects;

/**
 * The PharmacyTechnician class represents an employee who is a pharmacy technician, with a state
 * license ID and cashier training.
 */
public class PharmacyTechnician extends Employee {

  private String stateLicenseId;
  private boolean isCashierTrained;

  public PharmacyTechnician(String name, String phoneNumber,
      Address address, String employeeID,
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

  @Override
  public void dispenseMedication() {
//        TODO: a pharm tech would Assist pharmacist with filling prescription
//    i.e. retrieving meds from inventory, labeling the medication, and preparing it for dispensing,
//    as well as processing insurance claims and maintaining inventory records.
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
