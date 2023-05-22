package setup;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import misc.CashierRunnable;
import misc.ConcurrentCustomerLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacy.Pharmacy;

public class CashierRunnablePool {

    private static final Logger LOG = LogManager.getLogger(CashierRunnablePool.class);

    private int poolSize;
    private BlockingQueue<CashierRunnable> pool;
    private ConcurrentLinkedQueue<CashierRunnable> waitingPool;
    private Pharmacy pharmacy;
    private ConcurrentCustomerLine customerLine;

    public CashierRunnablePool(int poolSize, Pharmacy pharmacy,
        ConcurrentCustomerLine customerLine) {
        this.poolSize = poolSize;
        this.pharmacy = pharmacy;
        this.customerLine = customerLine;
        pool = new LinkedBlockingDeque<>(poolSize);
        waitingPool = new ConcurrentLinkedQueue<>();
        initializePool();
    }

    private void initializePool() {
        for (int i = 0; i < poolSize; i++) {
            addCashierRunnable(createCashierRunnable());
        }
    }

    private CashierRunnable createCashierRunnable() {
        return new CashierRunnable(pharmacy, customerLine);
    }

    public void addCashierRunnable() {
        addCashierRunnable(createCashierRunnable());
    }

    public CashierRunnable getCashierRunnable() throws InterruptedException {
        CashierRunnable cashierRunnable = pool.take();
        LOG.info(cashierRunnable.getCashier().getName() + " taken from the cashierPool");
        if (!waitingPool.isEmpty()) {
            String cashierOnBoard = waitingPool.peek().getCashier().getName();
            pool.put(waitingPool.poll());
            LOG.info("Moved " + cashierOnBoard + " from waitingPool to cashierPool");
        }
        return cashierRunnable;
    }

    public void addCashierRunnable(CashierRunnable cashierRunnable) {
        String cashierName = cashierRunnable.getCashier().getName();
        if (pool.size() == poolSize) {
            LOG.warn("CashierPool is full");
            waitingPool.offer(cashierRunnable);
            LOG.info("Cashier " + cashierName + " added to waiting pool");
        } else {
            try {
                pool.put(cashierRunnable);
                LOG.info("Cashier " + cashierName + " added to cashierPool");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
