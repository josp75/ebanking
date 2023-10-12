package cm.studyup.app;

import cm.studyup.app.entities.CurrentAccount;
import cm.studyup.app.entities.Customer;
import cm.studyup.app.entities.Operation;
import cm.studyup.app.entities.SavingAccount;
import cm.studyup.app.enums.AccountStatus;
import cm.studyup.app.enums.OperationType;
import cm.studyup.app.repositories.BankAccountRepository;
import cm.studyup.app.repositories.CustomerRepository;
import cm.studyup.app.repositories.OperationRepository;
import cm.studyup.app.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }



    @Bean
    CommandLineRunner start(
            BankAccountRepository bankAccountRepository,
            CustomerRepository customerRepository,
            OperationRepository operationRepository
    ) {
        return args -> {
            operationRepository.deleteAll();
            bankAccountRepository.deleteAll();
            customerRepository.deleteAll();

            Stream.of("Jospin", "Pierre", "Jordan").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name.toLowerCase() + "@gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRates(5.5);
                bankAccountRepository.save(savingAccount);
            });


            bankAccountRepository.findAll().forEach(acc -> {
                for (int i = 0; i < 2; i++) {
                    Operation operation = new Operation();
                    operation.setAmount(Math.random() * 12000);
                    operation.setOperationDate(new Date());
                    operation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    operation.setBankAccount(acc);
                    operationRepository.save(operation);

                }
            });
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(BankService bankService) {
        return args -> {
            bankService.consult();
        };
    }
}
