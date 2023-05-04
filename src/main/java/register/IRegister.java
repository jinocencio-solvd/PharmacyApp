package register;

import person.Customer;
import product.Product;

public interface IRegister {

    public void scanProduct(Product product);

    public void processTransaction();

    public double getTotal();

    public void logReceipt();
}
