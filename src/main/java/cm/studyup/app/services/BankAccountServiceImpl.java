package cm.studyup.app.services;

import cm.studyup.app.entities.*;
import cm.studyup.app.repositories.BankAccountRepository;
import cm.studyup.app.repositories.CustomerRepository;
import cm.studyup.app.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private OperationRepository operationRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving new Customer");
        return customerRepository.save(customer);
    }

    @Override
    public BankAccount saveBankAccount(double initialBalance, String type, Long customerId) {
        log.info("Saving new BankAccount");

        BankAccount bankAccount;
        if(type.equals("current")){
            bankAccount = new CurrentAccount();
        }else{
            bankAccount = new SavingAccount();
        }
        bankAccount.setId(UUID.randomUUID().toString());
        return null;
    }

    @Override
    public BankAccount getAccount(String accountId) {
        return null;
    }

    @Override
    public List<Customer> listCustomer() {
        return null;
    }

    @Override
    public void debit(String accountId, double amount, String description) {

    }

    @Override
    public void credit(String accountId, double amount, String description) {

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) {

    }

    @Override
    public Operation saveOperation(Operation operation) {
        return null;
    }
}
