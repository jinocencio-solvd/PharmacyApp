import java.util.Objects;

/**
 * The Person class represents a person with a name, phone number, and address.
 */
public class Person {
    private String name;
    private String phoneNumber;
    private Address address;

    public Person(String name, String phoneNumber, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and setters for name, phoneNumber and address
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getName().equals(person.getName())
                && getPhoneNumber().equals(person.getPhoneNumber())
                && getAddress().equals(person.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhoneNumber(), getAddress());
    }
}
