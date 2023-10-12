package cm.studyup.app.mappers;

import cm.studyup.app.dtos.CurrentAccountDto;
import cm.studyup.app.dtos.CustomerDto;
import cm.studyup.app.dtos.OperationDto;
import cm.studyup.app.dtos.SavingAccountDto;
import cm.studyup.app.entities.CurrentAccount;
import cm.studyup.app.entities.Customer;
import cm.studyup.app.entities.Operation;
import cm.studyup.app.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDto fromCustomer(Customer customer) {
        CustomerDto customerDTO = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDto(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

    public SavingAccountDto fromSavingBankAccount(SavingAccount savingAccount) {
        SavingAccountDto savingAccountDto = new SavingAccountDto();
        BeanUtils.copyProperties(savingAccount, savingAccountDto);
        savingAccountDto.setCustomerDto(fromCustomer(savingAccount.getCustomer()));
        savingAccountDto.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDto;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingAccountDto savingAccountDto) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDto, savingAccount);
        savingAccount.setCustomer(fromCustomerDto(savingAccountDto.getCustomerDto()));
        return savingAccount;
    }

    public CurrentAccountDto fromCurrentAccount(CurrentAccount currentAccount) {
        CurrentAccountDto currentAccountDto = new CurrentAccountDto();
        BeanUtils.copyProperties(currentAccount, currentAccountDto);
        currentAccountDto.setCustomerDto(fromCustomer(currentAccount.getCustomer()));
        currentAccountDto.setType(currentAccount.getClass().getSimpleName());
        return currentAccountDto;
    }

    public CurrentAccount fromCurrentAccountDto(CurrentAccountDto currentAccountDto) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDto, currentAccount);
        currentAccount.setCustomer(fromCustomerDto(currentAccountDto.getCustomerDto()));
        return currentAccount;
    }

    public OperationDto fromOperation(Operation operation) {
        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);
        return operationDto;
    }

}

