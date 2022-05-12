package ma.enset.bank_api.repositories;

import ma.enset.bank_api.entities.BankAccount;
import ma.enset.bank_api.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
