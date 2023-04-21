import java.util.Objects;

/**
 * The Product class represents an abstract product with a name, price, and quantity.
 */
public abstract class Product {

  protected String name;
  protected double price;

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
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

  @Override
  public String toString() {
    return "Product{" +
        "name='" + name + '\'' +
        ", price=" + price +
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
    return Double.compare(product.getPrice(), getPrice()) == 0 && Objects.equals(
        getName(), product.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPrice());
  }
}