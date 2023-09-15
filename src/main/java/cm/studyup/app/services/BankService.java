package cm.studyup.app.services;

import cm.studyup.app.entities.BankAccount;
import cm.studyup.app.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consult() {
        BankAccount bankAccount =
                bankAccountRepository.findAll().stream().findFirst().orElse(null);

        if(bankAccount == null)
            return;
        else {
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getClass().getName());
            bankAccount.getOperations().forEach(operation -> {
                System.out.println(operation.getAmount());

            });
        }
    }
}
