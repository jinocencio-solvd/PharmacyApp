package person;

import genericLinkedList.CustomerLine;

public interface ICashier {
    default AbstractCustomer getCustomerFromCustomerLine(CustomerLine customerLine){
        return customerLine.getNextCustomer();
    }
}
