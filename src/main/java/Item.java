/**
 * The Product class represents a product with a name, price, and quantity.
 */
public class Item extends Product {
    private String skuId;

    public Item(String name, double price, int quantity, String skuId) {
        super(name, price, quantity);
        this.skuId = skuId;
    }
    public Item(String name, double price, int quantity) {
        super(name, price, quantity);
        this.skuId = "0000-0000000-0";
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "skuId='" + skuId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                "} " + super.toString();
    }
}