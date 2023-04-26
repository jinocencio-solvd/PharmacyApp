import static org.junit.jupiter.api.Assertions.*;

import Exceptions.InsufficientQuantityException;
import Exceptions.ProductDoesNotExistException;
import Inventory.Inventory;
import Product.Item;
import Product.Medication;
import Product.Product;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {

    Inventory inventory = new Inventory();
    // Create products
    Item product1 = new Item("Band-Aids", 2.99);
    Item product2 = new Item("Hydrogen Peroxide", 1.99);
    Item product3 = new Item("Antacid Tablets", 4.99);
    Item product4 = new Item("Cough Drops", 3.49);
    Medication medication1 = new Medication("Aspirin", "500 mg", 0.10);
    Medication medication2 = new Medication("Ibuprofen", "200 mg", 0.08);
    Medication medication3 = new Medication("Acetaminophen", "325 mg", 0.05);
    Medication medication4 = new Medication("Naproxen", "220 mg", 0.12);
    Medication medication5 = new Medication("FakeMedication", "220 mg", 0.12);
    Map<Product, Integer> products = new HashMap<>();

    @BeforeEach
    void setUp() {
        products.put(product1, 50);
        products.put(product2, 25);
        products.put(product3, 30);
        products.put(product4, 40);
        products.put(medication1, 100);
        products.put(medication2, 50);
        products.put(medication3, 200);
        products.put(medication4, 75);
        inventory.addProduct(product2, 25);
        inventory.addProduct(product3, 30);
        inventory.addProduct(product1, 50);
        inventory.addProduct(product4, 40);
        inventory.addProduct(medication1, 100);
        inventory.addProduct(medication2, 50);
        inventory.addProduct(medication3, 200);
        inventory.addProduct(medication4, 75);
    }

    @Test
    void addProduct() {
        assertEquals(products, inventory.getProducts());
    }

    @Test
    void removeProduct() throws ProductDoesNotExistException, InsufficientQuantityException {
        inventory.removeProduct(medication4, 75);
        products.put(medication4, 0);
        assertEquals(products, inventory.getProducts());
        inventory.addProduct(medication4, 75);
        assertNotEquals(products, inventory.getProducts());
    }

    @Test
    void testRemoveProductWithInsufficientQuantity() {
        assertThrows(InsufficientQuantityException.class, () -> {
            inventory.removeProduct(medication4, 1000);
        });
        assertDoesNotThrow(() -> {
            inventory.removeProduct(medication4, 5);
        });
    }

    @Test
    void testProductDoesNotExist() {
        assertThrows(ProductDoesNotExistException.class, () -> {
            inventory.removeProduct(medication5, 1000);
        });
    }

    @Test
    void getQuantity() throws ProductDoesNotExistException, InsufficientQuantityException {
        assertEquals(75, inventory.getQuantity(medication4));
        inventory.addProduct(medication4, 75);
        assertEquals(150, inventory.getQuantity(medication4));
        inventory.removeProduct(medication4, 10);
        assertEquals(140, inventory.getQuantity(medication4));
    }

    @Test
    void testEquals() {
        assertEquals(products, inventory.getProducts());
    }

    @Test
    void testHashCode() throws ProductDoesNotExistException, InsufficientQuantityException {
        assertEquals(products.hashCode(), inventory.getProducts().hashCode());
        inventory.removeProduct(medication4, 10);
        assertNotEquals(products.hashCode(), inventory.getProducts().hashCode());

    }
}