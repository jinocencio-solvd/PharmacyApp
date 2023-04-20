import java.util.Objects;

/**
 * The Product class represents an abstract product with a name, price, and quantity.
 */
public abstract class Product {

  protected String name;
  protected double price;
  protected int quantity;

  public Product(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Product{" +
        "name='" + name + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product)) {
      return false;
    }
    Product product = (Product) o;
    return Double.compare(product.getPrice(), getPrice()) == 0
        && getQuantity() == product.getQuantity() && getName().equals(product.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPrice(), getQuantity());
  }
}