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

## Iter-3

### Task - Defining and Enforcing Behaviors

Requirements:

- Add 5 interfaces to the existing hierarchy.
    - IPharmacy, IInventory, ICustomer, IEmployee
- Use polymorphism with the abstract class and interface from the hierarchy.
    - In addition to abstract employee hierarchy, I abstracted a `Customer` class with its own
      interface with which the behavior is associated with the `Patient` and `Consumer` subclasses
- Create final class, method, variable.
    - The `PrescriptionRegistry` class is marked `final` and is not intended to change or derive
      from subclasses
    - The methods `Customer.addToCart()` and `Customer.removeFromCart()` implemented in the abstract
      class `Customer` are marked `final` as these methods are shared between `Consumer`
      and `Patient` and should not allow for method override by these subclasses.
    - Initializing the various inventory data structures (`Iventory.products`
      , `PrescriptionRegistry.prescriptionRegistry`) are marked `final`.
- Create a static block, method, variable.
    - `Patient` class has static variable `count` which counts the number of `Patient` instances.
      This number is accessed via the static method `Patient.getNumberOfPatients()`. Additionally,
      static methods were implemented to create pre-made instances of `Pharmacist`
      , `PharmacyTechnician`, and `Address`.
    - A static block was used to implement the `Employee` class counter, which is essentially the
      same function as the `Patient` class counter method.

## Iter-2

### Task - Continue Building

Requirements:

- Use polymorphism with at least one abstract class.
    - `Pharmacist` and `PhamacyTechnician` are examples of polymorphism with `Employee` being the
      abstract class.
    - `Product` was also made an abstract class with `Medication` and `Item` being the subclasses
- Create and override at least one abstract method.
    - See below.
- Use at protected modifier at least 5 times
    - Protected modifiers are used as class parameters for the `Person` class which are called in
      the protected method `printDetails`. This method is called within the `Pharmacist`
      and `PharmacyTechnician` subclasses in their respective `printEmployeeDetails` method, which
      implements the `abstract method`  from the `Employee` superclass.
- Override methods from class Object (toString(), hashcode(), equals()) for at least 3 classes from
  the hierarchy.
    - Each class contains these override methods.

### Major Changes Made

#### UML Diagram - iter2

![iter2UML](./media/iter2UML.png)

- Abstracted `Product` class and implemented `Item` class to reflect that Items and Medications are
  products provided by a pharmacy. Abstracting a product class further allows us to categorize other
  types of products like services or promotional offers like discounts, etc..
- Inventory data structure changed from an arraylist of products to a hashmap
- Inventory data structure (see challenges and notes)
- Additions made from requirements

### Iter-2 Challenges and Notes

- Prior to overriding the equals() and hashCode() methods of the subclass, it is necessary to first
  override these methods in the superclass. Otherwise, the subclass will only consider its own
  parameters when overriding equals() and hashCode().
- The initial implementation of the Inventory class used an ArrayList to store the Product objects,
  but it was difficult to define how the quantity of each product should be associated. It made more
  sense to consider the quantity of a product as a property of the inventory itself, rather than as
  a property of the Product object, as implemented in iteration 1. If we had decided to stick with
  the ArrayList, we would have had to represent the quantity of each product individually in the
  data structure. In this case, using a HashMap is a better choice to reflect these changes.

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

1. `Address class` - This class represents an Address object which contains the street, city, state,
   and postal code.

2. `Employee class` - Represents an employee that extends the Person class. Contains an employee ID
   field in addition to the Person class fields.

3. `Inventory class` - The Inventory class represents the inventory of a pharmacy, which consists of
   products and medications.

4. `Medications class` - The Medication class represents a medication with a name, dosage, price,
   and quantity.
5. `Patient class` - The Patient class represents a person who is a patient, with a patient ID and
   insurance information.
6. `Person class` - The Person class represents a person with a name, phone number, and address.

7. `Pharmacist class` - The Pharmacist class represents an employee who is a pharmacist, with a
   state license ID.
8. `PharmacyTechnician class` - The PharmacyTechnician class represents an employee who is a
   pharmacy technician, with a state license ID and cashier training.
9. `Prescription class` - The Prescription class represents a prescription with a prescription ID,
   number of refills, filled status, medication, and patient.
10. `Product class` - The Product class represents a product with a name, price, and quantity.

### Iter-1 Challenges

- Designing class hierarchies from the top-down, coding from the bottom-up. It is practically
  impossible to begin an OOP projects without having an initial design. For example, implementing a
  pharmacy that takes an inventory and employees must first have an instance of an inventory and an
  employee. Conversely, when an employee class inherits from a person class, the person class must
  first be implemented.