package utils;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import misc.CashierRunnable;
import misc.ConcurrentCustomerLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Pharmacist;
import pharmacy.Pharmacy;
import setup.CustomerLineSetup;
import setup.PharmacySetup;

public class ThreadsDemo {

    private static final Logger LOG = LogManager.getLogger(ThreadsDemo.class);

    private static Pharmacy userCreatePharmacy() {
        LOG.trace("In userCreatePatient");
        Pharmacy pharmacy = DataProvider.predefinedPharmacy();
        try (Scanner s = new Scanner(System.in)) {
            String name;
            System.out.println("Enter pharmacy name:");
            name = s.nextLine();
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            pharmacy.setName(name);
            LOG.trace("Pharmacy " + pharmacy.getName() + " created.");
            return pharmacy;
        } catch (IllegalArgumentException e) {
            LOG.warn("Name cannot be empty");
            userCreatePharmacy();
        }
        return pharmacy;
    }

    private static void pharmacistFillAllPrescriptions(Pharmacy pharmacy) {
        Pharmacist pharmacist = DataProvider.predefinedPharmacist()[0];
        Consumer<Pharmacy> runPharmacistFillAllRxReq = (Pharmacy p) ->
            pharmacist.fulfillAllPrescriptionLogRequests(
                p.getFilledPrescriptions(), p.getPrescriptionRequestLog(), p.getProductInventory(),
                p.getPrescriptionRegistry());

        runPharmacistFillAllRxReq.accept(pharmacy);
    }

    public static Pharmacy appSetup(boolean userCreateMode) {
        return userCreateMode ? PharmacySetup.setup(userCreatePharmacy()) : PharmacySetup.setup();
    }

    public static void threadOperations(Pharmacy pharmacy, int numCustomersInLine, int poolSize,
        int numWaitingThreads) {

        pharmacistFillAllPrescriptions(pharmacy);

        Runnable twoThreadsOperation = () -> {
            ConcurrentCustomerLine customerLine = new CustomerLineSetup(pharmacy,
                numCustomersInLine).setup();
            int numCustomerToAdd = 5;
            Thread addMoreCustomersToLine = new Thread(() -> {
                LOG.trace("Adding " + numCustomerToAdd + " customers from " + Thread.currentThread()
                    .getName());
                for (int i = 0; i < numCustomerToAdd; i++) {
                    customerLine.addCustomer();
                }
            });
            CashierRunnable processCustomers = new CashierRunnable(pharmacy, customerLine, 5);
            CashierRunnable processCustomers2 = new CashierRunnable(pharmacy, customerLine, 5);

            addMoreCustomersToLine.start(); // Start the first thread
            processCustomers.run(); // Start the second thread
            processCustomers2.run();
        };

        Runnable cashierPoolOperation = () -> {
            ConcurrentCustomerLine customerLine = new CustomerLineSetup(pharmacy,
                numCustomersInLine).setup();
            int totalThreads = poolSize + numWaitingThreads;
            CashierRunnablePool cashierRunnablePool = new CashierRunnablePool(poolSize, pharmacy,
                customerLine);
            for (int i = 0; i < numWaitingThreads; i++) {
                cashierRunnablePool.addCashierRunnable();
            }
            // Execute total amount of threads
            ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);
            for (int i = 0; i < totalThreads; i++) {
                try {
                    CashierRunnable fromPool = cashierRunnablePool.getCashierRunnable();
                    executorService.execute(fromPool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            executorService.shutdown();
        };

        Runnable completableFuturesOperation = () -> {
            CompletionStage<Void> cashierPoolOp = CompletableFuture.runAsync(cashierPoolOperation);
            CompletionStage<Void> twoThreadsOp = CompletableFuture.runAsync(twoThreadsOperation);
            CompletionStage<Void> combinedOp = cashierPoolOp.thenCompose(result -> twoThreadsOp);
            CompletableFuture<Void> cf = combinedOp.toCompletableFuture();
            try {
                cf.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        };
        LOG.warn("Starting twoThreadsOperation");
        twoThreadsOperation.run();
        LOG.warn("Starting cashierPoolOperation");
        cashierPoolOperation.run();
        LOG.warn("Starting CompletableFuturesOperation");
        completableFuturesOperation.run();
    }

    public static void main(String[] args) {
        Pharmacy pharmacy = appSetup(false);
        threadOperations(pharmacy, 7, 5, 2);
    }
}
