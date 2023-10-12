package cm.studyup.app.validation;


import cm.studyup.app.entities.Customer;
import cm.studyup.app.repositories.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        final List<Customer> customers = customerRepository.findByEmail(email.toLowerCase());
        System.out.println(customers);
        if(customers.isEmpty())
            return true;
        return customers.stream().map(e -> Objects.equals(e.getEmail(), email)).isParallel();
    }
}