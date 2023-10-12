package cm.studyup.app.services;

import cm.studyup.app.dtos.*;
import cm.studyup.app.entities.*;
import cm.studyup.app.exceptions.BalanceNotSufficientException;
import cm.studyup.app.exceptions.BankAccountNotFoundException;
import cm.studyup.app.exceptions.CustomerNotFoundException;
import cm.studyup.app.mappers.BankAccountMapperImpl;
import cm.studyup.app.repositories.BankAccountRepository;
import cm.studyup.app.repositories.CustomerRepository;
import cm.studyup.app.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private OperationRepository operationRepository;
    private BankAccountMapperImpl dtoMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("Saving new Customer");
        Customer customer = dtoMapper.fromCustomerDto(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public CurrentAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        log.info("Saving new current bankAccount");
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found");
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setOverDraft(overDraft);
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
        return dtoMapper.fromCurrentAccount(savedBankAccount);
    }

    @Override
    public SavingAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public List<CustomerDto> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) return new ArrayList<>();
        return customers.stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException {
        return null;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {

    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {

    }

    @Override
    public List<BankAccountDto> bankAccountList() {
        return null;
    }

    @Override
    public CustomerDto getCustomer(Long customerId) throws CustomerNotFoundException {
        final Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return dtoMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {

    }

    @Override
    public List<OperationDto> accountHistory(String accountId) {
        return null;
    }

    @Override
    public AccountHistoryDto getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        return null;
    }

    @Override
    public List<CustomerDto> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.searchCustomer(keyword);
        if (customers.isEmpty()) return new ArrayList<>();
        return customers.stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
    }
}

