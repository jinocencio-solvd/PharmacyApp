import java.util.ArrayList;

/**
 * The Inventory class represents the inventory of a pharmacy, which consists of products.
 */
public class Inventory {

  private ArrayList<Product> products;

  public Inventory(ArrayList<Product> products) {
    this.products = products;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return "Inventory{" +
        "products=" + products +
        '}';
  }
}