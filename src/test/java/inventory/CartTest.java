package inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pharmacy.Pharmacy;
import utils.CartSupplier;
import setup.PharmacySetup;

class CartTest {

    @Test
    void cartSupplier() {
        Pharmacy pharmacy = PharmacySetup.setup();

        Cart cart1 = new CartSupplier(pharmacy.getProductInventory(), 10).get();
        assertEquals(cart1.getNumberOfItemsInCart(), 10);
    }
}