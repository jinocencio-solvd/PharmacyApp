package register;

import static org.junit.jupiter.api.Assertions.*;

import inventory.Cart;
import misc.DataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Customer;
import person.PharmacyTechnician;

class RegisterTest {

    PharmacyTechnician[] technicians = DataProvider.predefinedPharmacyTechnicians();
    Customer[] customers = DataProvider.predefinedConsumers();

    PharmacyTechnician tech1 = technicians[0];
    Customer customer = customers[0];
    Cart itemsCart;
    Register register;

    @BeforeEach
    void setUp() {
        register = new Register(tech1);
        register.setCustomer(customer);
        itemsCart = DataProvider.predefinedCartItems();
    }

    @Test
    void getTotalForItemsOnly() {
        register.scanProduct(DataProvider.predefinedItems()[0]);
        register.scanProduct(DataProvider.predefinedItems()[0]);
        register.scanProduct(DataProvider.predefinedItems()[1]);
        assertEquals(7.97, register.getTotal());
    }

    @Test
    void scanProductForItemsOnly() {
        register.scanProduct(DataProvider.predefinedItems()[0]);
        assertEquals(1, register.getScannedProducts().size());
        register.scanProduct(DataProvider.predefinedItems()[0]);
        register.scanProduct(DataProvider.predefinedItems()[1]);
        assertEquals(3, register.getScannedProducts().size());
    }

    @Test
    void processTransaction() {
    }

    @Test
    void generateReceiptString() {
    }
}