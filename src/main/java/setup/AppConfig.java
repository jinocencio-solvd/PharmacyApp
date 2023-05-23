package setup;

public class AppConfig {
    //During setup
    public static boolean USER_CREATE_MODE = false;
    public static boolean SHOW_PHARMACY_SETUP = false;
    public static boolean SHOW_PRESCRIPTION_REGISTRY_LOGS = false;

    public static boolean SHOW_CUSTOMERS_IN_LINE = true;
    // CashierRunnable
    public static boolean SHOW_CASHIER_RECEIVED_CUSTOMER = true;
    public static boolean SHOW_CASHIER_FINISHED_TXN = false;
    public static boolean SHOW_CASHIER_TOTAL_CUSTOMERS = true;
    public static boolean SHOW_RX_STATUS_FLOW = false;

    public static final int NUM_PATIENTS = 1;
    public static final int NUM_CUSTOMERS = 3;
    public static final int TOTAL_CUSTOMERS = NUM_PATIENTS + NUM_CUSTOMERS;
    public static final int MAX_NUMBER_ITEMS_IN_CART = 15;
    public static final int NUM_CASHIERS = 2;

}

