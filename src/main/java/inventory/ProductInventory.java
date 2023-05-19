package inventory;

import exceptions.InsufficientQuantityException;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductOutOfStockException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import product.Item;
import product.Product;

public class ProductInventory extends Inventory {

    public ProductInventory() {
        super();
    }

    public Product getRandomProduct() {
        List<Product> productList = this.getProducts().keySet().stream()
            .filter(product -> product instanceof Item).collect(
                Collectors.toList());
        int randomIdx = new Random().nextInt(productList.size());
        Product randomProduct = productList.get(randomIdx);
        try {
            removeProduct(randomProduct, 1);
        } catch (InsufficientQuantityException | ProductDoesNotExistException | ProductOutOfStockException e) {
            getRandomProduct();
        }
        return randomProduct;
    }

    @Override
    public String toString() {
        return "Inventory.ProductInventory: " + super.toString();
    }
}
