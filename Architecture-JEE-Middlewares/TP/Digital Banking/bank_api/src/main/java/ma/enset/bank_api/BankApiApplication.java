package ma.enset.bank_api;

import ma.enset.bank_api.entities.AccountOperation;
import ma.enset.bank_api.entities.CurrentAccount;
import ma.enset.bank_api.entities.Customer;
import ma.enset.bank_api.entities.SavingAccount;
import ma.enset.bank_api.enums.AccountStatus;
import ma.enset.bank_api.enums.OperationType;
import ma.enset.bank_api.repositories.AccountOperationRepository;
import ma.enset.bank_api.repositories.BankAccountRepository;
import ma.enset.bank_api.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
										AccountOperationRepository accountOperationRepository
							)
	{
		return args -> {
			Stream.of("Bertrand","Hassan","cecile").forEach(
					cus->{
						Customer customer=new Customer();
						customer.setName(cus);
						customer.setEmail(cus+"@gmail.com");
						customerRepository.save(customer);
					}
			);

			customerRepository.findAll().forEach(
					cust->{
						CurrentAccount currentAccount=new CurrentAccount();
						currentAccount.setId(UUID.randomUUID().toString());
						currentAccount.setBalance(Math.random()*90000);
						currentAccount.setCreateAt(new Date());
						currentAccount.setStatus(AccountStatus.CREATED);
						currentAccount.setCustomer(cust);
						currentAccount.setOverDraft(90000);
						bankAccountRepository.save(currentAccount);

						SavingAccount savingAccount=new SavingAccount();
						savingAccount.setId(UUID.randomUUID().toString());
						savingAccount.setBalance(Math.random()*90000);
						savingAccount.setCreateAt(new Date());
						savingAccount.setStatus(AccountStatus.CREATED);
						savingAccount.setCustomer(cust);
						savingAccount.setInterestRate(5.5);
						bankAccountRepository.save(savingAccount);
					}
			);

			bankAccountRepository.findAll().forEach(
					acc->{
						for(int i=0;i<10;i++){
							AccountOperation accountOperation=new AccountOperation();
							accountOperation.setBankAccount(acc);
							accountOperation.setOperationDate(new Date());
							accountOperation.setAmount(Math.random()*12000);
							accountOperation.setType(Math.random()>0.5? OperationType.CREDIT:OperationType.DEBIT);
							accountOperationRepository.save(accountOperation);
						}
					}
			);



		};
	}
}
