package inventory;

import java.util.LinkedHashMap;
import product.Item;
import product.Product;

public class Cart extends Inventory {

    public Cart() {
        this.products = new LinkedHashMap<>();
    }

    public static Cart cartSupplier(ProductInventory productInventory, int numItems) {
        Cart cart = new Cart();
        for(int i = 0; i < numItems; i++){
            Product randomProduct = productInventory.getRandomProduct(Item.class);
            cart.addProduct(randomProduct, 1);
        }
        return cart;
    }

    public int getNumberOfItemsInCart(){
        return this.products.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String toString() {
        return "Inventory.Cart{" +
            super.toString() + "} ";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
