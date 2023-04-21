import java.util.Objects;

/**
 * The Product class represents a product with a name, price, and quantity.
 */
public class Item extends Product {

    private String skuId;

    public Item(String name, double price, String skuId) {
        super(name, price);
        this.skuId = skuId;
    }

    public Item(String name, double price) {
        super(name, price);
        this.skuId = "0000-0000000-0";
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Item item = (Item) o;
        return getSkuId().equals(item.getSkuId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSkuId());
    }
}