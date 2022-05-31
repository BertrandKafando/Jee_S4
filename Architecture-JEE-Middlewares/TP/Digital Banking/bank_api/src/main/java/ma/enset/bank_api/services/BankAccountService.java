package ma.enset.bank_api.services;

import ma.enset.bank_api.dtos.*;
import ma.enset.bank_api.entities.BankAccount;
import ma.enset.bank_api.exceptions.BalanceNotSufficientException;
import ma.enset.bank_api.exceptions.BankAccountNotFoundException;
import ma.enset.bank_api.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto updateCustomer(CustomerDto customer);
    void deleteCustomer(Long customerId);
    SavingAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
CurrentAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    List<CustomerDto>listCustomers();
    CustomerDto getcustomer(Long custometId) throws CustomerNotFoundException;
    BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException;
void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
void transfer(String accountIdsource,String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccountDto> bankAccountList();
    List<AccountOperationDto>accountsHistory(String accountId);
    AccountHistoryDto getaccountsHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
    List<CustomerDto> searchCustomers(String s);

    List<BankAccountDto>getcustomerAccount(Long Id ) throws CustomerNotFoundException;
}
