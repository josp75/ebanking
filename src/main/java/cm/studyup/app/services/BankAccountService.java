package cm.studyup.app.services;

import cm.studyup.app.entities.BankAccount;
import cm.studyup.app.entities.Customer;
import cm.studyup.app.entities.Operation;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);

    BankAccount saveBankAccount(double initialBalance, String type, Long customerId);

    BankAccount getAccount(String accountId);

    List<Customer> listCustomer();

    void debit(String accountId, double amount, String description);

    void credit(String accountId, double amount, String description);

    void transfer(String accountIdSource, String accountIdDestination, double amount);

    Operation saveOperation(Operation operation);

}
