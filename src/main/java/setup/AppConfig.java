package setup;

public class AppConfig {


    //During setup
    public static boolean USER_CREATE_MODE = false;
    public static boolean SHOW_PHARMACY_SETUP = false;
    public static boolean SHOW_CUSTOMERS_IN_LINE = true;
    public static boolean SHOW_PRESCRIPTION_REGISTRY_LOGS = false;
    // CashierRunnable
    public static boolean DISABLE_CASHIER_RUNNABLE_CUSTOMER_LIMIT = true;
    public static boolean SHOW_CASHIER_RECEIVED_CUSTOMER = true;
    public static boolean SHOW_CASHIER_FINISHED_TXN = true;
    public static boolean SHOW_CASHIER_TOTAL_CUSTOMERS = false;

    public static boolean SHOW_RECEIPTS = false;

    public static final int MAX_NUMBER_ITEMS_IN_CART = 15;
    public static final int NUM_CUSTOMERS = 5;
    public static final int NUM_CASHIERS = 2;
}
