package setup;

import inventory.Cart;
import inventory.ProductInventory;
import java.util.function.Supplier;
import product.Item;
import product.Product;

public class CartSupplier implements Supplier<Cart> {
    private final ProductInventory productInventory;
    private final int numItems;

    public CartSupplier(ProductInventory productInventory, int numItems) {
        this.productInventory = productInventory;
        this.numItems = numItems;
    }

    @Override
    public Cart get() {
        Cart cart = new Cart();
        for (int i = 0; i < numItems; i++) {
            Product randomProduct = productInventory.getRandomProduct(Item.class);
            cart.addProduct(randomProduct, 1);
        }
        return cart;
    }
}