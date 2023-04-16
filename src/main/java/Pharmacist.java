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
                '}';
    }
}
