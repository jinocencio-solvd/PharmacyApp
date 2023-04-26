# PharmacyApp

## About This Project:

This repository describes an Object-Oriented Programming (OOP) project portraying a simple pharmacy.
The project is designed using classes and objects to model various aspects of a pharmacy, including
medications, patients, pharmacists, pharmacy technicians, prescriptions, and products.

<details>
<summary>Automatic Testing with GitHub Actions and Maven</summary>

This project deploys automatic testing by using the `Surefire Plugin` for Maven to run unit tests
and GitHub Actions to automatically build and test the Java Maven package. Every time code changes
are pushed to the repository, GitHub Actions is triggered to run tests and ensure that the code is
working as expected. Successful builds and tests are indicated with a green checkmark next to the
commit ID while failed builds or tests are indicated with a red X .

The `maven.yml` file in the `.github/workflows` directory of this repository contains the
configuration for GitHub Actions to run the tests.

The tests are composed of class unit tests using `Junit` and are located in `src/test/java/`. By
employing automatic testing, we can catch issues early in the development process and ensure that
our code is always working as expected. This helps to maintain the quality of the code and make sure
that the project is stable and reliable.

</details>

## Iter-4

### Task - Exceptions and Logging

Requirements:

- Create 5 custom exceptions.
    - `InsufficientQuantityException` - Thrown to indicate that the requested quantity of a product
      is not available in the inventory.
    - `ProductDoesNotExistException` - Thrown when a requested product does not exist in the
      inventory.
    - `ProductOutOfStockException` - Thrown when a product is out of stock in the inventory or there
      are no product left in cart.
    - `DuplicatePersonException` - Thrown when an employee is already in the employee database or
      when a registered patient attempts to re-register into the prescription registry.
    - `PersonDoesNotExistException` - Thrown when an employee is failed to be retrieved from the
      employee database or when an unregistered patient adds a prescription to the prescription
      registry.
    - `NoMoreRefillsException` - Thrown when a prescription has no more refills available.
- Handle exceptions in 2 ways.
    - **Propagate the exception** - `Inventory.removeProduct` method handles the exceptions by
      using `throws` if the product is not available in the inventory, does not exist, or has
      insufficient quantity. Hence, the exception is indirectly handled by propagating the exception
      to be handled by its method caller. In one such case
      is `Pharmacist.retrieveMedicationsFromInventory.`
    - **try-catch block** - The `Pharmacist.retrieveMedicationsFromInventory` method directly
      handles the exception using a try-catch block where it catches the exception that is thrown
      by `Inventory.removeProduct`.
- Use try-catch with resources.
    - try-catch with resources was used in `Main.userCreatePatient`. This method demonstrates the
      use of try-catch with resources in creating a patient record within the pharmacy application.
      It uses a `Scanner` object to receive input from the user, and then tries to create a new
      patient record using the input. If the user enters an empty name, it throws
      an `IllegalArgumentException`, which is caught by the catch block that logs a warning message
      and then recursively calls the userCreatePatient() method to prompt the user to enter a
      non-empty name. Finally, the Scanner object is closed using the `finally` block.
- Log messages to the console, file.
    - log4j2 logs message to console using the config file found in `src/main/resources/log4j2.xml`
      and adding the XML snippet below at the `<Appenders>` level.
    ```xml 
    <Console name="console" target="SYSTEM_OUT">
      <PatternLayout
        pattern="%highlight{%d{HH:mm:ss.SSS} %-5level %logger{36}.%M - %msg%n}{FATAL=red, ERROR=red, WARN=yellow, INFO=green, DEBUG=blue, TRACE=white}"
        disableAnsi="false"/>
    </Console>
    ```
    - Similarly, outputting log messages to file was accomplished by adding the XML snippet also in
      the `<Appenders>` level. The following file is generated in the root directory and is
      named `PharmacyAppLogs.log`
    ```xml 
         <File name="file" fileName="PharmacyAppLogs.log" append="false">
           <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %logger{36}.%M - %msg%n" disableAnsi="false"/>
         </File>
    ```
    - Then we add bothAppender references `AppenderRef` to the `root` logger.

### Major Changes Made

- Refactored code into packages
- Added log4j2 logging
- Created, implemented (throw and catch) and tested custom exceptions
- Created a test user interactive demo for creating a patient that utilized try-catch with resources
  for a Scanner object.

### Challenges and Notes

While it is usually considered best practice to handle exceptions as close to the source of the
issue as possible, there are cases where it may be more appropriate to handle them in the calling
method instead. For example, when a duplicate employee is registered into the employee database
using the `Pharmacy.hireEmployee` method, the database can throw an exception back up to the caller
where it is handled. Similarly, when a patient with a prescription attempts to re-register using
the `prescriptionRegistry.addPatientToRegistry` method, a `duplicatePersonsException` is thrown if a
duplicate patient entry is detected. In this case, the database can handle the exception by
redirecting to the `prescriptionRegistry.addPrescription` method in the catch block, allowing the
prescription associated with the original patient to be added to the registry instead.

## Iter-3

### Task - Defining and Enforcing Behaviors

Requirements:

- Add 5 interfaces to the existing hierarchy.
    - `Pharmacy.IPharmacy`, `Inventory.IInventory`, `Person.ICustomer`, `Person.IEmployee`
      , `PrescriptionRegistry.IPrescriptionRegistry`
- Use polymorphism with the abstract class and interface from the hierarchy.
    - In addition to abstract employee hierarchy, I abstracted a `Person.Customer` class with its
      own interface with which the behavior is associated with the `Person.Patient`
      and `Person.Consumer` subclasses
- Create final class, method, variable.
    - The `PrescriptionRegistry.PrescriptionRegistry` class is marked `final` and is not intended to
      change or derive from subclasses
    - The methods `Person.Customer.addToCart()` and `Person.Customer.removeFromCart()` implemented
      in the abstract class `Person.Customer` are marked `final` as these methods are shared
      between `Person.Consumer`
      and `Person.Patient` and should not allow for method override by these subclasses.
    - Initializing the various inventory data structures (`Iventory.products`
      , `PrescriptionRegistry.PrescriptionRegistry.prescriptionRegistry`) are marked `final`.
- Create a static block, method, variable.
    - `Person.Patient` class has static variable `count` which counts the number of `Person.Patient`
      instances. This number is accessed via the static
      method `Person.Patient.getNumberOfPatients()`. Additionally, static methods were implemented
      to create pre-made instances of `Person.Pharmacist`
      , `Person.PharmacyTechnician`, and `Misc.Address`.
    - A static block was used to implement the `Person.Employee` class counter, which is essentially
      the same function as the `Person.Patient` class counter method.

#### UML Diagram - iter3

![iter3UML](./media/iter3UML.png)

- Refactored insurance info from patient class.
- Abstract Person.Customer class was implemented to better represent pharmacy clients i.e. Patients
  and Customers.
- Interfaces were implemented to model and enforce class behaviors
- Data structures used to hold information were implemented primarily using hashmaps or arraylists
- Static methods were used to implement predefined classes.

### Challenges and Notes

- One challenge encountered in Iter-2 was determining which behaviors belonged to which class. For
  example, when modeling the scenario of a patient offering their prescription to the pharmacy, I
  needed to identify the two key steps involved: 1) The patient offers their prescription, and 2)
  Although the current implementation handles both steps in the patient class, it may be beneficial
  to split the responsibilities across multiple classes for improved organization and
  maintainability.

## Iter-2

### Task - Continue Building

Requirements:

- Use polymorphism with at least one abstract class.
    - `Person.Pharmacist` and `PhamacyTechnician` are examples of polymorphism
      with `Person.Employee` being the abstract class.
    - `Product.Product` was also made an abstract class with `Product.Medication` and `Product.Item`
      being the subclasses
- Create and override at least one abstract method.
    - See below.
- Use at protected modifier at least 5 times
    - Protected modifiers are used as class parameters for the `Person.Person` class which are
      called in the protected method `printDetails`. This method is called within
      the `Person.Pharmacist`
      and `Person.PharmacyTechnician` subclasses in their respective `printEmployeeDetails` method,
      which implements the `abstract method`  from the `Person.Employee` superclass.
- Override methods from class Object (toString(), hashcode(), equals()) for at least 3 classes from
  the hierarchy.
    - Each class contains these override methods.

### Major Changes Made

#### UML Diagram - iter2

![iter2UML](./media/iter2UML.png)

- Abstracted `Product.Product` class and implemented `Product.Item` class to reflect that Items and
  Medications are products provided by a pharmacy. Abstracting a product class further allows us to
  categorize other types of products like services or promotional offers like discounts, etc..
- Inventory.Inventory data structure changed from an arraylist of products to a hashmap
- Inventory.Inventory data structure (see challenges and notes)
- Additions made from requirements

### Challenges and Notes

- Prior to overriding the equals() and hashCode() methods of the subclass, it is necessary to first
  override these methods in the superclass. Otherwise, the subclass will only consider its own
  parameters when overriding equals() and hashCode().
- The initial implementation of the Inventory.Inventory class used an ArrayList to store the
  Product.Product objects, but it was difficult to define how the quantity of each product should be
  associated. It made more sense to consider the quantity of a product as a property of the
  inventory itself, rather than as a property of the Product.Product object, as implemented in
  iteration 1. If we had decided to stick with the ArrayList, we would have had to represent the
  quantity of each product individually in the data structure. In this case, using a HashMap is a
  better choice to reflect these changes.

## Iter-1

### Task - Create class hierarchy

Requirements:

- At least 10 classes
- All classes must contain properties (minimum 1)
- At least 5 private properties (in total, in any classes)
- All private variables must have getters and setters.
- All classes should have at least one custom constructor
- Create separate class with main() which will instantiate objects of implemented classes.
  </br>

#### UML Diagram - iter1

![iter1UML](./media/iter1UML.png)

1. `Misc.Address class` - This class represents an Misc.Address object which contains the street,
   city, state, and postal code.

2. `Person.Employee class` - Represents an employee that extends the Person.Person class. Contains
   an employee ID field in addition to the Person.Person class fields.

3. `Inventory.Inventory class` - The Inventory.Inventory class represents the inventory of a
   pharmacy, which consists of products and medications.

4. `Medications class` - The Product.Medication class represents a medication with a name, dosage,
   price, and quantity.
5. `Person.Patient class` - The Person.Patient class represents a person who is a patient, with a
   patient ID and insurance information.
6. `Person.Person class` - The Person.Person class represents a person with a name, phone number,
   and address.

7. `Person.Pharmacist class` - The Person.Pharmacist class represents an employee who is a
   pharmacist, with a state license ID.
8. `Person.PharmacyTechnician class` - The Person.PharmacyTechnician class represents an employee
   who is a pharmacy technician, with a state license ID and cashier training.
9. `Product.Prescription class` - The Product.Prescription class represents a prescription with a
   prescription ID, number of refills, filled status, medication, and patient.
10. `Product.Product class` - The Product.Product class represents a product with a name, price, and
    quantity.

### Challenges and Notes

- Designing class hierarchies from the top-down, coding from the bottom-up. It is practically
  impossible to begin an OOP projects without having an initial design. For example, implementing a
  pharmacy that takes an inventory and employees must first have an instance of an inventory and an
  employee. Conversely, when an employee class inherits from a person class, the person class must
  first be implemented.