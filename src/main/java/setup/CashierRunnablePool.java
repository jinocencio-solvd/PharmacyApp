package setup;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import misc.CashierRunnable;
import misc.ConcurrentCustomerLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Employee;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;

public class CashierRunnablePool {

    private static final Logger LOG = LogManager.getLogger(CashierRunnablePool.class);

    private int poolSize;
    private Employee cashier;
    private ConcurrentCustomerLine customerLine;
    private Pharmacy pharmacy;
    private BlockingQueue<CashierRunnable> pool;

    public CashierRunnablePool(Pharmacy pharmacy,
        ConcurrentCustomerLine customerLine, int poolSize) {
        this.customerLine = customerLine;
        this.pharmacy = pharmacy;
        this.poolSize = poolSize;
        pool = new LinkedBlockingDeque<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            pool.add(createCashierRunnable());
        }
    }

    public CashierRunnable getCashierRunnable() throws InterruptedException {
        return pool.take();
    }

    public void completeCashierRunnable(CashierRunnable cashierRunnable) {
        boolean cashierRunnableReturned = pool.offer(cashierRunnable);
        if (cashierRunnableReturned) {
            Employee cashier = cashierRunnable.getCashier();
            PharmacyTechnicianSupplier.returnTechnician((PharmacyTechnician) cashier);
            LOG.info("CashierRunnable returned. " + cashier.getName() + " went on break.");
        }
    }

    public void addCashierRunnable(CashierRunnable cashierRunnable) {
        pool.add(cashierRunnable);
    }

    public void addCashierRunnable() {
        pool.add(createCashierRunnable());
    }

    private CashierRunnable createCashierRunnable() {
        return new CashierRunnable(pharmacy, customerLine);
    }


}
