import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * TODO: Implement Specialty class
 * String[] specialties = new String[]{
 * "ambulatory care pharmacy",
 * "cardiology pharmacy",
 * "compounded sterile preparations pharmacy",
 * "critical care pharmacy",
 * "emergency medicine pharmacy",
 * "geriatric pharmacy",
 * "infectious diseases pharmacy",
 * "nuclear pharmacy",
 * "nutrition support pharmacy",
 * "oncology pharmacy",
 * "pediatric pharmacy",
 * "pharmacotherapy",
 * "psychiatric pharmacy and solid organ transplantation pharmacy"
 * };
 */

class PharmacyTest {
    Pharmacy defaultPharmacy;
    Pharmacy noSpecialties;
    Pharmacy pharmacy1;


    @BeforeEach
    void setUp() {
        defaultPharmacy = new Pharmacy();
        noSpecialties = new Pharmacy("noSpecialties", "000-000-0000",
                "noSpecialties@email.com");
        pharmacy1 = new Pharmacy("pharmacy1", "111-111-1111", "pharmacy1" +
                "@email.com");
    }

    @Test
    void getName() {
        assertEquals("defaultPharmacyName", defaultPharmacy.getName());
        assertEquals("pharmacy1", pharmacy1.getName());
    }

    @Test
    void setName() {
        defaultPharmacy.setName("changedDefaultPharmacyName");
        assertEquals("changedDefaultPharmacyName", defaultPharmacy.getName());
        pharmacy1.setName("changedPharmacy1");
        assertEquals("changedPharmacy1", pharmacy1.getName());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("123-456-7890", defaultPharmacy.getPhoneNumber());
        assertEquals("000-000-0000", noSpecialties.getPhoneNumber());
        assertEquals("111-111-1111", pharmacy1.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        assertEquals("123-456-7890", defaultPharmacy.getPhoneNumber());
        defaultPharmacy.setPhoneNumber("999-456-7890");
        assertEquals("999-456-7890", defaultPharmacy.getPhoneNumber());

        assertEquals("000-000-0000", noSpecialties.getPhoneNumber());
        noSpecialties.setPhoneNumber("999-000-0000");
        assertEquals("999-000-0000", noSpecialties.getPhoneNumber());

        assertEquals("111-111-1111", pharmacy1.getPhoneNumber());
        pharmacy1.setPhoneNumber("999-111-1111");
        assertEquals("999-111-1111", pharmacy1.getPhoneNumber());

    }

    @Test
    void getEmailAddress() {
        assertEquals("defaultEmailAddress@email.com",
                defaultPharmacy.getEmailAddress());
        assertEquals("noSpecialties@email.com",
                noSpecialties.getEmailAddress());
        assertEquals("pharmacy1@email.com", pharmacy1.getEmailAddress());
    }

    @Test
    void setEmailAddress() {
        assertEquals("defaultEmailAddress@email.com",
                defaultPharmacy.getEmailAddress());
        defaultPharmacy.setEmailAddress("defaultEmailAddress@changedEmail.com");
        assertEquals("defaultEmailAddress@changedEmail.com",
                defaultPharmacy.getEmailAddress());

        assertEquals("noSpecialties@email.com",
                noSpecialties.getEmailAddress());
        noSpecialties.setEmailAddress("noSpecialties@changedEmail.com");
        assertEquals("noSpecialties@changedEmail.com",
                noSpecialties.getEmailAddress());

        assertEquals("pharmacy1@email.com", pharmacy1.getEmailAddress());
        pharmacy1.setEmailAddress("pharmacy1@changedEmail.com");
        assertEquals("pharmacy1@changedEmail.com", pharmacy1.getEmailAddress());
    }


}