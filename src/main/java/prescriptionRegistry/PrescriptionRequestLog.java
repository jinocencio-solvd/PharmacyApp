package prescriptionRegistry;

import static setup.AppConfig.SHOW_RX_STATUS_FLOW;

import genericLinkedList.GenericQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrescriptionRequestLog extends GenericQueue<Prescription> {

    private static final Logger LOG = LogManager.getLogger(PrescriptionRequestLog.class);

    public void addPrescriptionRequest(Prescription prescription) {
        if (SHOW_RX_STATUS_FLOW) {
            LOG.info("Pharmacy added Rx for " + prescription.getPatient().getName() + " to RxRequestLog and is waiting to be processed by pharmacist. Rx status remains as "
                + prescription.getPrescriptionStatus());
        }
        enqueue(prescription);
    }

    public Prescription getNextPrescriptionRequest() {
        if (isEmpty()) {
            LOG.warn("The prescriptionRequestLog is empty");
            return null;
        }
        return dequeue();
    }

    public int getPrescriptionRequestLogLength() {
        return size();
    }
}
