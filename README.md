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

## Iter-10

### Task

Requirements

- Create 2 Threads using Runnable and Thread.
    - In `Main.threadOperations`, the runnable `twoThreadsOperation` is composed of a `Thread` that
      adds customers to a concurrent customer line.
- Create Connection Pool. Use collection from java.util.concurrent package. Connection class may be
  mocked. The pool should be threadsafe and lazy initialized.
    - Connection Pool was simulated in the implementation of `utils/CashierRunnablePool`. This
      threadsafe implementation includes a `BlockingQueue` to limit the pool size and
      a `ConcurrentLinkedQueue`
      to handle waiting connections (cashierRunnables). `CashierRunnablePool.initializePool()`
      method utilizes lazy implementation by being called in the constructor and creating
      new `CashierRunnable`'s to populate the `cashierRunnablePool`.
- Initialize Connection Pool object of size 5. Load Connection Pool using single threads and Java
  Thread Pool (7 threads in total). 5 threads should be able to get the connection.2 Threads should
  wait for the next available connection. The program should wait as well.
    - In `Main.threadOperations`, the runnable `cashierPoolOperation` initializes the
      cashierRunnablePool with the given parameters in the `main` method. Two
      more `cashierRunnables` were added to the pool after the pool was initialized to `poolSize`
      capacity and waited in the `waitingPool` to enter after the first two runnables were completed
      and vacated from the `cashierRunnablepool`.
- Implement previous point but with interfaces Future and CompletableStage.
    - In `Main.threadOperations`, the runnable `completableFuturesOperation` utilized
      2 `CompletionStages` instances that ran `cashierPoolOperation` and `twoThreadsOperation`
      using `CompletableFuture.runAsync`. Then, the instances were combined using `.thenCompose` to
      chain the completion of `cashierPoolOp` with `twoThreadsOp`. Lastly, `.toCompletableFuture`
      was called on the combined operation to convert it to a `CompletableFuture` and using
      the `.get` method to wait for all `CompleteStage` to complete. 

## Iter-9

### Task

Requirements

- Add 7 collection streaming in the hierarchy with terminal and non-terminal operations.
    - `.map()` was used in `Register.getTotal()` to map each product's cost to
      the `Register.calculatePrice` method.
        - `.reduce()` method was then called after `.map` on the resulting stream with an initial
          value of `0.0` and `Double::sum` method reference as the binary operator.
    - `.flatMap()` was used in `Register.scanAllProductsInCart` to flatten streams of duplicated
      same products into a single stream of products.
        - `.forEach` was then used to apply the `scanProduct` method for each product in the stream.
    - `filter()` was used in `FilledPrescriptions.getFilledPrescriptionsByPatient` to filter filled
      prescriptions by patient.
        - `.collect()` was used as a terminal operation to collect the filtered prescriptions into a
          list.
    - `.generate()` was used in `Pharmacist.getMedicationsFromInventory` to generate a stream of
      medication objects.
        - `.limit()` was used to limit the number of medications generated in the stream to then be
          collected into a list using `.collect(Collectors.toList)` as the terminal operation.
- Using reflection extract information(modifiers, return types, parameters, etc) about fields,
  constructors, methods. Create object and call method using the only reflection.
    - Reflection examples are implemented in `misc/ReflectionPharmacy` which can be run using
      the `main` method in `ReflectionPharmacy`

#### Prescription Status Flow

![iter9PrescriptionStatusFlow](media/iter9PrescriptionStatusFlow.png)

## Iter-8

### Task - Lambda functions and Enums

Requirements

- Use at least 5 lambda functions from the java.util.function package.
    - `Predicate<T>` was used in `Pharmacy.isOpen()` to create a function that checks for the
      given `DayOfWeek` as a `BusinessDay` enum and whether the business is open on that day by
      checking the `BusinessDay` field `isOpen`. The `DayOfWeek` input is applied to
      the `Predicate.test` method and the result is returned as the output of the isOpen method.
    - `Consumer<T>` was used in `Main.prescriptionOperations()` to
      call `Pharmacist.fulfillAllPrescriptionLogRequests`. The function is subsequently used in the
      for loop to demonstrate max number of times for prescription refilling.
    - `Function<T,R>` was used in `Register.calculateDiscountPrice()` to transform and read
      an `AbstractCustomer` as a `Patient` during runtime.
    - `Supplier<T>` was used in `AbstractCustomer.increaseCreditBalanceByOneHundred()` to supply the
      patient with an increase of 100 to the customer's creditBalance.
    - `BooleanSupplier` is used in `AbstractCustomer.isPatient()` to return a boolean value if the
      method was invoked from the Patient class
- Create 3 custom Lambda functions with generics.
    - `customLambdaFunctions/PerformOperation` defines `PerformOperation<T>` that takes an object
      to `perform` an operation of that object.
    - `customLambdaFunctions/Repeater` defines `Repeater<T>` which
      invokes `repeat(int numRepeats, PerformOperation<T> operation)`. This lambda function repeats
      a `PerformOperation` by `numRepeats` amount of times.
    - `customLambdaFunctions/NullChecker` defines `NullChecker<T>` which returns a boolean value in
      regard to if the object passed into the invoke method `isNull` is a null value.
- Create 5 complex Enums(with fields, methods, blocks).
    - `BusinessDays` include the days of the week and has `.id`, `.description`, and `.isOpen`
      fields. Methods include field getters and a `getBusinessDay()` method.
    - `PrescriptionStatus` include the various prescription states that are allowed. The status are
      set and modified by appropriate authorities i.e. initialized as `NEW`
      if unregistered, `PENDING` if received by pharmacy, `COMPLETED` and `REFILL_UPON_REQUEST` by
      pharmacist. `FILLED` will be implemented upon completion of patient transaction.
    - `ProductType` represent the different types of products that the pharmacy carries.
      All `Medications` are initialized with ProductType enum `PRESCRIBED_MEDICINE`. Products can be
      initialized upon instantiation or set with the setter method. Otherwise, all other products
      and items are `UNLABELED` by default.
    - `PaymentType` represents the different types of payment a customer can provide to complete
      their transaction at the register. Thus, `PaymentType` is a field implemented in `Register`
      . `NOT_SELECTED` is the default enum and can be changed by either the employee operating the
      register or the customer in future development.
    - `EmployeeType` represents the different types of employee the pharmacy can have. By default,
      all employees are `FULL_TIME` employees.

## Iter-7

### Task - File Reader

Requirements:

- Read text from the file and calculate the numbers of the unique words. Write the result to the
  file. The main requirement is: using StringUtils and FileUtils to implement it with minimum lines
  of code.
    - The `StringUtils.split` method was used to convert the contents of the text file into an array
      of strings which can be processed to count the number of unique words. This method is similar
      to using the `String.split` method, but is provided by the Apache Commons Lang library.
    - `FileUtils.readFileToString` method was used to read the contents of the text file and convert
      it to a string. This method simplifies the process of reading the file by avoiding the need to
      manually loop through the file and read individual lines using the `BufferedReader`
      and `FileReader` approach.
    - To write the output to a file, the `FileUtils.writeStringToFile` method was used. This method
      allows us to write a customized message to the file, with the option to append the message to
      an existing file. The append parameter is set to true to append the message to the existing
      file, and avoids the need to initialize a `FileWriter` with the `append` parameter set
      to `true`, manually writing the message to the file `FileWriter.write`, and then closing the
      writer `FileWriter.close`.

## Iter-6

### Task - Maven Support

Requirements:

- Move all projects to Maven.
    - Project initially created using Intellij Maven Java Project starter
    - Alternatively, we could have used `mvn archetype:generate` to initialize the project
      ```
      mvn archetype:generate -DgroupId=org.example -DartifactId=PharmacyApp
      -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
      -DinteractiveMode=false
      ```

    - Build jar file and deploy to the local repository.
      ```
      mvn deploy:deploy-file -Dfile=target/PharmacyApp-1.0-SNAPSHOT.jar -DgroupId=org.example
      -DartifactId=PharmacyApp -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Durl=file://~/.m2/repository/
        ```
        - In order to build a jar file for our app that includes the `log4j2` dependency, we have to
          add the Maven plugins `maven-assembly-plugin` and `maven-jar-plugin` into the `pom.xml`
          file
- Add and use 2 Plugins.
    - `maven-assembly-plugin` - used to create JAR files with all dependencies included.
    - `maven-jar-plugin` - creates executable JAR file with manifest configuration and location of
      the application entry point.
    - `maven-surefire-plugin` - used to run tests during the build process.
- Run mvn for different phases from the Maven lifecycle. Check the result.
    - clean - removes generated `target` folder from previous build.
    - test - runs all test classes and methods in `src/test`.
    - package - packages the application into a JAR file.
    - site - generates project's site documentation in the `target/site` directory.

## Iter-5

### Task - Collections and Generics

Requirements:

- Add 5 collections to the hierarchy.
    - The `PrescriptionRegistry` class uses `HashMap<Customer, ArrayList<Prescription>` as its data
      store.
    - Within the `PrescriptionRegistry` data store, the `HashMap` uses a `Customer` as the key and
      an `ArrayList<Prescription>` as the values.
    - To ensure uniqueness among a collection of `Employee` objects, a `LinkedHashSet` was used to
      implement the Employee data store.
    - Cart uses a `LinkedHashMap<Product, Integer>` to preserve order and inherits from Inventory
    - `Inventory` implements a `Map<Product, Integer>`.
        - `Cart` is a subclass of `Inventory` and takes advantage of the map hierarchy by
          implementing `Map` in the superclass and `LinkedHashMap` in the subclass.
- Create custom implementation of LinkedList with generic and use it in your project.
    - A generic linked list is implemented with `.addFirst`, `.addLast`, `.removeFirst`
      , `.removeLast`, `.getFirst`, `.getLast`, `.size`, `.printList`, `isEmpty`,`.get`, `.insertAt`
      and `size` operations. Methods are tested in `GenericLinkedListTest`
        - Inherited from the generic linked list above, a generic queue data structure was
          implemented that supported the `.enque`, `.dequeue`, and `.peek` operations. Subsequently,
          the `CustomerLine` class inherits from the generic queue data structure which takes
          the `Customer` as its parameterized type and supports the `.addCustomer`
          , `.getNextCustomer`, `.getLineLength`, `.isCustomerPatient` methods.

### Major Changes Made

- The pharmacy's employees was originally used an `ArrayList` as its datastore. However, to ensure
  uniqueness of employees, the collection changed from using `ArrayList` to `LinkedHashSet`.
- Implementation of `CustomerLine` using generic LinkedList / generic queue.

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
    - **Propagate the exception** - `inventory.removeProduct` method handles the exceptions by
      using `throws` if the product is not available in the inventory, does not exist, or has
      insufficient quantity. Hence, the exception is indirectly handled by propagating the exception
      to be handled by its method caller. In one such case
      is `Pharmacist.retrieveMedicationsFromInventory.`
    - **try-catch block** - The `Pharmacist.retrieveMedicationsFromInventory` method directly
      handles the exception using a try-catch block where it catches the exception that is thrown
      by `inventory.removeProduct`.
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
using the `pharmacy.hireEmployee` method, the database can throw an exception back up to the caller
where it is handled. Similarly, when a patient with a prescription attempts to re-register using
the `prescriptionRegistry.addPatientToRegistry` method, a `duplicatePersonsException` is thrown if a
duplicate patient entry is detected. In this case, the database can handle the exception by
redirecting to the `prescriptionRegistry.addPrescription` method in the catch block, allowing the
prescription associated with the original patient to be added to the registry instead.

## Iter-3

### Task - Defining and Enforcing Behaviors

Requirements:

- Add 5 interfaces to the existing hierarchy.
    - `pharmacy.IPharmacy`, `inventory.IInventory`, `person.ICustomer`, `person.IEmployee`
      , `prescriptionRegistry.IPrescriptionRegistry`
- Use polymorphism with the abstract class and interface from the hierarchy.
    - In addition to abstract employee hierarchy, I abstracted a `person.AbstractCustomer` class
      with its own interface with which the behavior is associated with the `person.Patient`
      and `person.Customer` subclasses
- Create final class, method, variable.
    - The `prescriptionRegistry.prescriptionRegistry` class is marked `final` and is not intended to
      change or derive from subclasses
    - The methods `person.AbstractCustomer.addToCart()`
      and `person.AbstractCustomer.removeFromCart()` implemented in the abstract
      class `person.AbstractCustomer` are marked `final` as these methods are shared
      between `person.Customer`
      and `person.Patient` and should not allow for method override by these subclasses.
    - Initializing the various inventory data structures (`Iventory.products`
      , `prescriptionRegistry.prescriptionRegistry.prescriptionRegistry`) are marked `final`.
- Create a static block, method, variable.
    - `person.Patient` class has static variable `count` which counts the number of `person.Patient`
      instances. This number is accessed via the static
      method `person.Patient.getNumberOfPatients()`. Additionally, static methods were implemented
      to create pre-made instances of `person.Pharmacist`
      , `person.PharmacyTechnician`, and `misc.Address`.
    - A static block was used to implement the `person.Employee` class counter, which is essentially
      the same function as the `person.Patient` class counter method.

#### UML Diagram - iter3

![iter3UML](media/iter3UML.png)

- Refactored insurance info from patient class.
- Abstract person.AbstractCustomer class was implemented to better represent pharmacy clients i.e.
  Patients and Customers.
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
    - `person.Pharmacist` and `PhamacyTechnician` are examples of polymorphism
      with `person.Employee` being the abstract class.
    - `product.product` was also made an abstract class with `product.Medication` and `product.Item`
      being the subclasses
- Create and override at least one abstract method.
    - See below.
- Use at protected modifier at least 5 times
    - Protected modifiers are used as class parameters for the `person.person` class which are
      called in the protected method `printDetails`. This method is called within
      the `person.Pharmacist`
      and `person.PharmacyTechnician` subclasses in their respective `printEmployeeDetails` method,
      which implements the `abstract method`  from the `person.Employee` superclass.
- Override methods from class Object (toString(), hashcode(), equals()) for at least 3 classes from
  the hierarchy.
    - Each class contains these override methods.

### Major Changes Made

#### UML Diagram - iter2

![iter2UML](media/iter2UML.png)

- Abstracted `product.product` class and implemented `product.Item` class to reflect that Items and
  Medications are products provided by a pharmacy. Abstracting a product class further allows us to
  categorize other types of products like services or promotional offers like discounts, etc..
- inventory.inventory data structure changed from an arraylist of products to a hashmap
- inventory.inventory data structure (see challenges and notes)
- Additions made from requirements

### Challenges and Notes

- Prior to overriding the equals() and hashCode() methods of the subclass, it is necessary to first
  override these methods in the superclass. Otherwise, the subclass will only consider its own
  parameters when overriding equals() and hashCode().
- The initial implementation of the inventory.inventory class used an ArrayList to store the
  product.product objects, but it was difficult to define how the quantity of each product should be
  associated. It made more sense to consider the quantity of a product as a property of the
  inventory itself, rather than as a property of the product.product object, as implemented in
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

![iter1UML](media/iter1UML.png)

1. `misc.Address class` - This class represents an misc.Address object which contains the street,
   city, state, and postal code.

2. `person.Employee class` - Represents an employee that extends the person.person class. Contains
   an employee ID field in addition to the person.person class fields.

3. `inventory.inventory class` - The inventory.inventory class represents the inventory of a
   pharmacy, which consists of products and medications.

4. `Medications class` - The product.Medication class represents a medication with a name, dosage,
   price, and quantity.
5. `person.Patient class` - The person.Patient class represents a person who is a patient, with a
   patient ID and insurance information.
6. `person.person class` - The person.person class represents a person with a name, phone number,
   and address.

7. `person.Pharmacist class` - The person.Pharmacist class represents an employee who is a
   pharmacist, with a state license ID.
8. `person.PharmacyTechnician class` - The person.PharmacyTechnician class represents an employee
   who is a pharmacy technician, with a state license ID and cashier training.
9. `prescriptionRegistry.Prescription class` - The prescriptionRegistry.Prescription class
   represents a prescription with a prescription ID, number of refills, filled status, medication,
   and patient.
10. `product.product class` - The product.product class represents a product with a name, price, and
    quantity.

### Challenges and Notes

- Designing class hierarchies from the top-down, coding from the bottom-up. It is practically
  impossible to begin an OOP projects without having an initial design. For example, implementing a
  pharmacy that takes an inventory and employees must first have an instance of an inventory and an
  employee. Conversely, when an employee class inherits from a person class, the person class must
  first be implemented.