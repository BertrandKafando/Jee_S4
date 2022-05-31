package ma.enset.bank_api.repositories;

import ma.enset.bank_api.entities.BankAccount;
import ma.enset.bank_api.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    List<BankAccount> findBankAccountByCustomer(Customer customer);
}
