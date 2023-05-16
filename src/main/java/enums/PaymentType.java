package enums;

public enum PaymentType {
    CASH(0, "Cash Payment"),
    CREDIT_CARD(1, "Credit Card Payment"),
    DEBIT_CARD(2, "Debit Card Payment"),
    WIRELESS_PAY(3, "Wireless Payment"),
    NOT_SELECTED(4, "Must Select a payment type");

    private int id;
    private String description;

    PaymentType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
