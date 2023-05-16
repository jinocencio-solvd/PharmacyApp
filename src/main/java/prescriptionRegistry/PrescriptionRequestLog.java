package prescriptionRegistry;

import genericLinkedList.GenericQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrescriptionRequestLog extends GenericQueue<Prescription> {

    private static final Logger LOG = LogManager.getLogger(PrescriptionRequestLog.class);

    public void addPrescriptionRequest(Prescription prescription) {
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
