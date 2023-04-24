public abstract class Customer extends Person implements ICustomer {

    public Customer(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
    }
}