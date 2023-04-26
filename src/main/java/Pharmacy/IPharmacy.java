package Pharmacy;

import Exceptions.DuplicatePersonException;
import Person.Employee;

/**
 * The Pharmacy.IPharmacy interface defines methods for hiring and releasing employees and printing
 * information about the pharmacy.
 */
public interface IPharmacy {

    /**
     * Hires a new employee and adds them to the pharmacy's list of employees.
     *
     * @param newEmployee the person to hire as an employee
     */
    void hireEmployee(Employee newEmployee) throws DuplicatePersonException;

    /**
     * Releases an employee and removes them from the pharmacy's list of employees.
     *
     * @param employee the employee to release from their position
     */
    void releaseEmployee(Employee employee);

    /**
     * Prints information about the pharmacy, including its name, address, phone number, and a list
     * of current employees.
     */
    void printPharmacyInformation();
}
