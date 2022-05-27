package ma.enset.bank_api.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.bank_api.dtos.*;
import ma.enset.bank_api.entities.*;
import ma.enset.bank_api.enums.OperationType;
import ma.enset.bank_api.exceptions.BalanceNotSufficientException;
import ma.enset.bank_api.exceptions.BankAccountNotFoundException;
import ma.enset.bank_api.exceptions.CustomerNotFoundException;
import ma.enset.bank_api.mappers.BankAccountMaperImpl;
import ma.enset.bank_api.repositories.AccountOperationRepository;
import ma.enset.bank_api.repositories.BankAccountRepository;
import ma.enset.bank_api.repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor

@Slf4j  //pour la journalisation
public class BankAccountServiceImpl implements  BankAccountService{
    private CustomerRepository customerRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountRepository bankAccountRepository;
    private BankAccountMaperImpl maper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        log.info("Saving customer");
      Customer savedcustomer=   customerRepository.save(maper.fromcutomerDto(customer));
        return maper.fromcutomer(savedcustomer) ;
    }
    @Override
    public CustomerDto updateCustomer(CustomerDto customer){
        Customer savedcustomer=   customerRepository.save(maper.fromcutomerDto(customer));
        return maper.fromcutomer(savedcustomer) ;
    }

    @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }



    @Override
    public SavingAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotFoundException("Customer Not found");

       SavingAccount bankAccount=new SavingAccount ();

        bankAccount.setCustomer(customer);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreateAt(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setInterestRate(interestRate);
        SavingAccount  saveSavingAccount =bankAccountRepository.save(bankAccount);
        return maper.fromSavingAccount(saveSavingAccount) ;
    }

    @Override
    public CurrentAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotFoundException("Customer Not found");

        CurrentAccount bankAccount=new CurrentAccount();

        bankAccount.setCustomer(customer);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreateAt(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setOverDraft(overDraft);
        CurrentAccount saveCurrentAccount=bankAccountRepository.save(bankAccount);

        return maper.fromCurrentAccount(saveCurrentAccount);
    }



    @Override
    public List<CustomerDto> listCustomers() {
        List<Customer>customers=customerRepository.findAll();
     List<CustomerDto>  customerDtos=  customers.stream().map(
                p->maper.fromcutomer(p)
        ).collect(Collectors.toList());
         return customerDtos;
    }

    @Override
    public CustomerDto getcustomer(Long custometId) throws CustomerNotFoundException {
     Customer customer=   customerRepository.findById(custometId)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));
        return maper.fromcutomer(customer);
    }

    @Override
    public BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("account not Found"));
        if(bankAccount instanceof SavingAccount){
            SavingAccount savingAccount=(SavingAccount) bankAccount;
            return maper.fromSavingAccount(savingAccount);
        }
        else  {
            CurrentAccount currentAccount=(CurrentAccount) bankAccount;
            return maper.fromCurrentAccount(currentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("account not Found"));
        if(bankAccount.getBalance()<amount)
            throw  new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);


    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("account not Found"));
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdsource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
    debit(accountIdsource,amount,"transfer from + "+accountIdsource);
    credit(accountIdDestination,amount,"transfer to + "+accountIdDestination);
    }

    @Override
    public List<BankAccountDto> bankAccountList(){
     List<BankAccount> bankAccounts= bankAccountRepository.findAll();
   List<BankAccountDto>bankAccountDtos=  bankAccounts.stream().map(
                bankAccount -> {
                    if(bankAccount  instanceof SavingAccount){
                        SavingAccount savingAccount=(SavingAccount) bankAccount;
                        return maper.fromSavingAccount(savingAccount);
                    }
                    else{
                        CurrentAccount currentAccount=(CurrentAccount) bankAccount;
                        return maper.fromCurrentAccount(currentAccount);
                    }
                }
        ).collect(Collectors.toList());
   return bankAccountDtos;
    }


    @Override
    public List<AccountOperationDto>accountsHistory(String accountId){
    List<AccountOperation> accountOperations=   accountOperationRepository.findByBankAccountId(accountId);
    List<AccountOperationDto> accountOperationDtos=accountOperations.stream().map(
            accountOperation -> maper.fromAccountOperation(accountOperation)

    ).collect(Collectors.toList());
    return accountOperationDtos;
    }

    @Override
    public AccountHistoryDto getaccountsHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("account not Found"));
       Page<AccountOperation> operations=  accountOperationRepository.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page,size));
        AccountHistoryDto accountHistoryDto=new AccountHistoryDto();
        accountHistoryDto.setAccountId(accountId);
        accountHistoryDto.setAccountOperationDtos(
                operations.getContent().stream().map(
                        p->maper.fromAccountOperation(p)
                ).collect(Collectors.toList())
        );
        accountHistoryDto.setCurrentPage(page);
        accountHistoryDto.setPageSize(size);
        accountHistoryDto.setBalance(bankAccount.getBalance());
        accountHistoryDto.setTotalPages(operations.getTotalPages());
        return accountHistoryDto;
    }

    @Override
    public List<CustomerDto> searchCustomers(String keyword) {
        List<Customer> customers=customerRepository.searchCustomer(keyword);
        List<CustomerDto> customerDTOS = customers.stream().map(cust -> maper.fromcutomer(cust)).collect(Collectors.toList());
        return customerDTOS;
    }

}
