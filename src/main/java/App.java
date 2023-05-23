import java.util.function.Consumer;
import misc.CashierRunnable;
import misc.ConcurrentCustomerLine;
import person.Pharmacist;
import pharmacy.Pharmacy;
import setup.AppConfig;
import setup.CustomerLineSetup;
import setup.PharmacySetup;
import utils.DataProvider;

public class App {
    private final Pharmacy pharmacy;
    private final ConcurrentCustomerLine customerLine;
    private static void pharmacistFillAllPrescriptions(Pharmacy pharmacy) {
        Pharmacist pharmacist = DataProvider.predefinedPharmacist()[0];
        Consumer<Pharmacy> runPharmacistFillAllRxReq = (Pharmacy p) ->
            pharmacist.fulfillAllPrescriptionLogRequests(
                p.getFilledPrescriptions(), p.getPrescriptionRequestLog(), p.getProductInventory(),
                p.getPrescriptionRegistry());

        runPharmacistFillAllRxReq.accept(pharmacy);
    }
    public App() {
        pharmacy = PharmacySetup.setup();
        customerLine = new CustomerLineSetup(pharmacy,
            AppConfig.TOTAL_CUSTOMERS).setup();
        pharmacistFillAllPrescriptions(pharmacy);
    }

    public void run() {
        for (int i = 0; i < AppConfig.NUM_CASHIERS; i++){
            Thread thread = new Thread(new CashierRunnable(pharmacy, customerLine));
            thread.start();
        }
    }
}
