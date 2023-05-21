package setup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.PharmacyTechnician;

public class PharmacyTechnicianSupplier implements Supplier<PharmacyTechnician> {

    private static final Logger LOG = LogManager.getLogger(PharmacyTechnicianSupplier.class);

    private static List<PharmacyTechnician> pharmTechList = new ArrayList<>(
        List.of(DataProvider.predefinedPharmacyTechnicians()));
    private static Queue<PharmacyTechnician> availableTechs = new LinkedList<>(pharmTechList);

    @Override
    public PharmacyTechnician get() {
        if (availableTechs.isEmpty()) {
            // All technicians have been used, reset the available technicians queue
            availableTechs.addAll(pharmTechList);
        }
        return availableTechs.poll();
    }

    public static void returnTechnician(PharmacyTechnician technician) {
        availableTechs.offer(technician);
    }

    public void addTechnician(PharmacyTechnician technician) {
        pharmTechList.add(technician);
        availableTechs.offer(technician);
    }

    public boolean hasAvailableTechnicians() {
        return !availableTechs.isEmpty();
    }

    public int getAvailableTechnicianCount() {
        return availableTechs.size();
    }
}