package cm.studyup.app.repositories;

import cm.studyup.app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

      List<Customer> findByEmail(String email);
      @Query("select c from Customer c where c.name like :kw")
      List<Customer> searchCustomer(@Param("kw") String keyword);
}
