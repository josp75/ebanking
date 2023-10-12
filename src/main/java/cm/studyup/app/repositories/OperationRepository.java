package cm.studyup.app.repositories;

import cm.studyup.app.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByBankAccountId(String accountId);

    Page<Operation> findByBankAccountOrderByOperationDateDesc(String accountId, Pageable pageable);
}
