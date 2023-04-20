import java.util.ArrayList;

/**
 * The Inventory class represents the inventory of a pharmacy, which consists of products.
 */
public class Inventory {

  private ArrayList<Product> products;

  public Inventory(ArrayList<Product> products) {
    this.products = products;
  }

  public Inventory() {
    this.products = new ArrayList<Product>();
  }

  /**
   * Adds a new Product to the products inventory.
   *
   * @param newProduct the Product to add
   */
  public void addProductToInventory(Product newProduct) {
    products.add(newProduct);
  }

  /**
   * Removes a Product from the products inventory.
   *
   * @param productToRemove the Product to remove
   */
  public void removeProductFromInventory(Product productToRemove) {
    products.remove(productToRemove);
  }

  /**
   * Increases the quantity of the specified Product by the given amount.
   *
   * @param productName   the name of the Product to increase the quantity of
   * @param quantityToAdd the amount to increase the quantity by
   * @throws IllegalArgumentException if the specified Product is not found
   */
  public void increaseProductQuantity(String productName, int quantityToAdd) {
    for (Product product : products) {
      if (product.getName().equals(productName)) {
        int currentQuantity = product.getQuantity();
        product.setQuantity(currentQuantity + quantityToAdd);
        return;
      }
    }
    throw new IllegalArgumentException("Product not found: " + productName);
  }

  /**
   * Decreases the quantity of the specified Product by the given amount.
   *
   * @param productName        the name of the Product to decrease the quantity of
   * @param quantityToSubtract the amount to decrease the quantity by
   * @throws IllegalArgumentException if the specified Product is not found
   */
  public void decreaseProductQuantity(String productName, int quantityToSubtract) {
    for (Product product : products) {
      if (product.getName().equals(productName)) {
        int currentQuantity = product.getQuantity();
        product.setQuantity(currentQuantity - quantityToSubtract);
        return;
      }
    }
    throw new IllegalArgumentException("Product not found: " + productName);
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return "Inventory{" + "products=" + products + '}';
  }
}