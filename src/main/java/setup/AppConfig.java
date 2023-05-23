package setup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = AppConfig.class.getResourceAsStream(
            "/AppConfig.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final boolean USER_CREATE_MODE = Boolean.parseBoolean(
        properties.getProperty("USER_CREATE_MODE"));
    public static final boolean SHOW_PHARMACY_SETUP = Boolean.parseBoolean(
        properties.getProperty("SHOW_PHARMACY_SETUP"));
    public static final boolean SHOW_PRESCRIPTION_REGISTRY_LOGS = Boolean.parseBoolean(
        properties.getProperty("SHOW_PRESCRIPTION_REGISTRY_LOGS"));
    public static final boolean SHOW_CUSTOMERS_IN_LINE = Boolean.parseBoolean(
        properties.getProperty("SHOW_CUSTOMERS_IN_LINE"));
    public static final boolean SHOW_CASHIER_RECEIVED_CUSTOMER = Boolean.parseBoolean(
        properties.getProperty("SHOW_CASHIER_RECEIVED_CUSTOMER"));
    public static final boolean SHOW_CASHIER_FINISHED_TXN = Boolean.parseBoolean(
        properties.getProperty("SHOW_CASHIER_FINISHED_TXN"));
    public static final boolean SHOW_CASHIER_TOTAL_CUSTOMERS = Boolean.parseBoolean(
        properties.getProperty("SHOW_CASHIER_TOTAL_CUSTOMERS"));
    public static final boolean SHOW_RX_STATUS_FLOW = Boolean.parseBoolean(
        properties.getProperty("SHOW_RX_STATUS_FLOW"));
    public static final boolean SHOW_RECEIPT = Boolean.parseBoolean(
        properties.getProperty("SHOW_RECEIPT"));
    public static final int NUM_PATIENTS = Integer.parseInt(properties.getProperty("NUM_PATIENTS"));
    public static final int NUM_CUSTOMERS = Integer.parseInt(
        properties.getProperty("NUM_CUSTOMERS"));
    public static final int TOTAL_CUSTOMERS = NUM_PATIENTS + NUM_CUSTOMERS;
    public static final int MAX_NUMBER_ITEMS_IN_CART = Integer.parseInt(
        properties.getProperty("MAX_NUMBER_ITEMS_IN_CART"));
    public static final int NUM_CASHIERS = Integer.parseInt(properties.getProperty("NUM_CASHIERS"));
}
