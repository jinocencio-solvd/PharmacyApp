package Misc;

import java.util.Objects;

/**
 * This class represents an Misc.Address object which contains the street, city, state, and postal code.
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

    public static Address[] predefinedAddresses() {
        return new Address[]{
            new Address("001 Pearl St", "Anytown", "CA", 12345),
            new Address("123 Main St", "Anytown", "CA", 12345),
            new Address("456 Elm St", "Anytown", "NY", 67890),
            new Address("789 Oak St", "Sometown", "FL", 24680),
            new Address("321 Pine St", "Othertown", "TX", 13579),
            new Address("111 Maple St", "Another Town", "GA", 97531),
            new Address("222 Cedar St", "Someplace", "VA", 86420)
        };
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
        return "Misc.Address{" +
            "street=" + street +
            ", city=" + city +
            ", state=" + state +
            ", postalCode=" + postalCode +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
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