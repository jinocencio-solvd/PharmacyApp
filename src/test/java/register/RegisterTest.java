package register;

import static org.junit.jupiter.api.Assertions.*;

import inventory.Cart;
import misc.DataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.AbstractCustomer;
import person.PharmacyTechnician;

class RegisterTest {

    PharmacyTechnician[] technicians = DataProvider.predefinedPharmacyTechnicians();
    AbstractCustomer[] abstractCustomers = DataProvider.predefinedConsumers();

    PharmacyTechnician tech1 = technicians[0];
    AbstractCustomer abstractCustomer = abstractCustomers[0];
    Cart itemsCart;
    Register register;

    @BeforeEach
    void setUp() {
        register = new Register(tech1);
        register.setCustomer(abstractCustomer);
        itemsCart = DataProvider.predefinedCarts()[0];

        register.setCustomer(abstractCustomer);
        register.setCart(itemsCart);
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
        assertFalse(register.isTransactionCompleted());
        abstractCustomer.setCreditBalance(1000.0);
        register.scanAllProductsInCart();
        register.processTransaction();
        assertTrue(register.isTransactionCompleted());
    }
}