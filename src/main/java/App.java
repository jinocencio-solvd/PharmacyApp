import misc.CashierRunnable;
import misc.ConcurrentCustomerLine;
import pharmacy.Pharmacy;
import setup.AppConfig;
import setup.CustomerLineSetup;
import setup.PharmacySetup;

public class App {
    private final Pharmacy pharmacy;
    private final ConcurrentCustomerLine customerLine;

    public App() {
        pharmacy = PharmacySetup.setup();
        customerLine = new CustomerLineSetup(pharmacy,
            AppConfig.NUM_CUSTOMERS).setup();
    }

    public void run() {
        for (int i = 0; i < AppConfig.NUM_CASHIERS; i++){
            Thread thread = new Thread(new CashierRunnable(pharmacy, customerLine));
            thread.start();
        }




    }
}
