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
    public String toString() {
        return "PharmacyTechnician{" +
                "stateLicenseId='" + stateLicenseId + '\'' +
                ", isCashierTrained=" + isCashierTrained +
                '}';
    }
}
