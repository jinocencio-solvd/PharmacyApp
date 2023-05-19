package inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pharmacy.Pharmacy;
import setup.PharmacySetup;

class CartTest {

    @Test
    void cartSupplier() {
        Pharmacy pharmacy = PharmacySetup.setup();

        Cart cart1 = Cart.cartSupplier(pharmacy.getProductInventory(), 10);
        assertEquals(cart1.getNumberOfItemsInCart(), 10);
    }
}