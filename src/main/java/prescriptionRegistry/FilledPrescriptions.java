package genericLinkedList;

import prescriptionRegistry.Prescription;

public class FilledPrescriptions extends GenericLinkedList<Prescription> {

    public void addFilledPresciption(Prescription prescription){
        addFirst(prescription);
    }

    public Prescription getPrescriptionByName(){
        return null;
    }
}
