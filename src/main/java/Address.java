import java.util.Objects;

/**
 * This class represents an Address object which contains the street, city,
 * state, and postal code.
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private int postalCode;

    public Address(String street, String city, String state, int postalCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street=" + street +
                ", city=" + city +
                ", state=" + state +
                ", postalCode=" + postalCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getPostalCode() == address.getPostalCode()
                && Objects.equals(getStreet(), address.getStreet())
                && Objects.equals(getCity(), address.getCity())
                && Objects.equals(getState(), address.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getCity(), getState(),
                getPostalCode());
    }
}