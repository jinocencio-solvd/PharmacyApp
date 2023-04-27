package inventory;

public class ProductInventory extends Inventory{

    public ProductInventory() {
        super();
    }

    @Override
    public String toString() {
        return "Inventory.ProductInventory: " + super.toString();
    }
}
