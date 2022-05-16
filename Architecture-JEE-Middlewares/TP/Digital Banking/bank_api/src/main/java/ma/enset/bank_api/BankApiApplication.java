package ma.enset.bank_api;

import ma.enset.bank_api.dtos.BankAccountDto;
import ma.enset.bank_api.dtos.CurrentAccountDto;
import ma.enset.bank_api.dtos.CustomerDto;
import ma.enset.bank_api.dtos.SavingAccountDto;
import ma.enset.bank_api.entities.*;
import ma.enset.bank_api.enums.AccountStatus;
import ma.enset.bank_api.enums.OperationType;
import ma.enset.bank_api.exceptions.BalanceNotSufficientException;
import ma.enset.bank_api.exceptions.BankAccountNotFoundException;
import ma.enset.bank_api.exceptions.CustomerNotFoundException;
import ma.enset.bank_api.repositories.AccountOperationRepository;
import ma.enset.bank_api.repositories.BankAccountRepository;
import ma.enset.bank_api.repositories.CustomerRepository;
import ma.enset.bank_api.services.BankAccountService;
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
			CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
			return  args -> {
				Stream.of("toto","Hassan","cecile").forEach(
						cus->{
							CustomerDto customer= new CustomerDto();
							customer.setName(cus);
							customer.setEmail(cus+"@gmail.com");
							bankAccountService.saveCustomer(customer);
						}
				);
				bankAccountService.listCustomers().forEach(
						cust->{
							try {
								bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000, cust.getId());
								bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5, cust.getId());



							} catch (CustomerNotFoundException e) {
								throw new RuntimeException(e);
							}

						}
				);


				for(BankAccountDto bankAccount:  bankAccountService.bankAccountList())
					for(int i=0;i<10;i++){
						String accounId;
						if(bankAccount instanceof SavingAccountDto){
							accounId=((SavingAccountDto) bankAccount).getId();
						}
						else accounId=((CurrentAccountDto) bankAccount).getId();

						bankAccountService.credit(accounId, 10000+Math.random()*120000,"Credit");
						bankAccountService.debit(accounId , 1000+Math.random()*9000,"Debit");
					}




			};

	}


	//@Bean
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
