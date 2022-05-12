package ma.enset.bank_api.repositories;

import ma.enset.bank_api.entities.AccountOperation;
import ma.enset.bank_api.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
