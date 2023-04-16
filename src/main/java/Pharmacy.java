/**
 * The Pharmacy class represents a pharmacy that has a name, phone number, email address, and an array of specialties.
 */
public class Pharmacy {
  private String name;
  private String phoneNumber;
  private String emailAddress;
  private String[] specialties;

  /**
   * Default constructor with no arguments
   */
  public Pharmacy() {
    this.name = "defaultPharmacyName";
    this.phoneNumber = "123-456-7890";
    this.emailAddress = "defaultEmailAddress@email.com";
    this.specialties = new String[]{};
  }

  /**
   * Constructs a new Pharmacy object with the specified name, phone number, email address, and specialties.
   *
   * @param name         the name of the pharmacy
   * @param phoneNumber  the phone number of the pharmacy
   * @param emailAddress the email address of the pharmacy
   * @param specialties  an array of specialties offered by the pharmacy
   */
  public Pharmacy(String name, String phoneNumber, String emailAddress, String[] specialties) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.specialties = specialties;
  }

  /**
   * Constructs a new Pharmacy object with the specified name, phone number, email address, and specialties.
   *
   * @param name         the name of the pharmacy
   * @param phoneNumber  the phone number of the pharmacy
   * @param emailAddress the email address of the pharmacy
   */
  public Pharmacy(String name, String phoneNumber, String emailAddress) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.specialties = new String[]{};
  }

  /**
   * Returns the name of the pharmacy.
   *
   * @return the name of the pharmacy
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the pharmacy
   *
   * @param name the new name of the pharmacy
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the phone number of the pharmacy.
   *
   * @return the phone number of the pharmacy
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the phone number of the pharmacy.
   *
   * @param phoneNumber the new phone number of the pharmacy
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns the email address of the pharmacy.
   *
   * @return the email address of the pharmacy
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Sets the email address of the pharmacy.
   *
   * @param emailAddress the new email address of the pharmacy
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * Returns the specialties offered by the pharmacy.
   *
   * @return the specialties offered by the pharmacy
   */
  public String[] getSpecialties() {
    return specialties;
  }

  /**
   * Sets the specialties offered by the pharmacy.
   *
   * @param specialties the new specialties offered by the pharmacy
   */
  public void setSpecialties(String[] specialties) {
    // TODO: Refactor to use addSpecialty and removeSpecialty methods instead
    // Simple set method
    this.specialties = specialties;
  }

}
