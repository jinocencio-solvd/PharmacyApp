import java.util.Objects;

/**
 * The Pharmacist class represents an employee who is a pharmacist, with a state license ID.
 */
public class Pharmacist extends Employee {

    private String stateLicenseId;

    public Pharmacist(String name, String phoneNumber, Address address, String employeeID,
        String stateLicenseId) {
        super(name, phoneNumber, address, employeeID);
        this.stateLicenseId = stateLicenseId;
    }

    public String getStateLicenseId() {
        return stateLicenseId;
    }

    public void setStateLicenseId(String stateLicenseId) {
        this.stateLicenseId = stateLicenseId;
    }

    public void retrieveMedicationsFromInventory(Inventory inventory, Prescription prescription) {
        Medication prescribedMed = prescription.getMedication();
        int prescribedQuantity = prescription.getPrescribedQuantity();
        inventory.removeProduct(prescribedMed, prescribedQuantity);
    }

    /**
     * Fills a prescription by removing the prescribed medication and quantity from the given
     * inventory, updating the prescription information, and printing a confirmation message to the
     * console.
     *
     * @param inventory    the inventory from which to remove the prescribed medication and
     *                     quantity
     * @param prescription the prescription to be filled
     */
    public void fillPrescription(Inventory inventory, Prescription prescription) {
        if (prescription.getNumRefills() == 0) {
            //TODO: implement proper error handling
            System.out.println("No more refills available");
            return;
        }

        retrieveMedicationsFromInventory(inventory, prescription);

        // complete prescription
        prescription.setNumRefills(prescription.getNumRefills() - 1);
        prescription.setFilled(true);

        // TODO: Upon patient picking up prescription, setFilled to false to be ready for next refill
        // Confirmation message
        String msg = "prescription: " + prescription.getPrescriptionId() + " for patient: "
            + prescription.getPatient().getName() + " is filled.";
        System.out.println(msg);
    }

    /**
     * method to print out employee details
     */
    @Override
    public void printEmployeeDetails() {
        super.printDetails();
        System.out.println("Position Title: Pharmacist");
        System.out.println("EmployeeId: " + super.employeeId);
        System.out.println("LicenseId: " + this.stateLicenseId);
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
            "stateLicenseId='" + stateLicenseId + '\'' +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pharmacist)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
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
