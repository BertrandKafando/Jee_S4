package ma.enset.bank_api.web;

import lombok.AllArgsConstructor;
import ma.enset.bank_api.dtos.*;
import ma.enset.bank_api.exceptions.BalanceNotSufficientException;
import ma.enset.bank_api.exceptions.BankAccountNotFoundException;
import ma.enset.bank_api.mappers.BankAccountMaperImpl;
import ma.enset.bank_api.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*") // pour donner l'accès au frontend
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;
    private BankAccountMaperImpl doMapper;
 @GetMapping("/accounts/{accountId}")
    public BankAccountDto getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return  bankAccountService.getBankAccount(accountId);

    }

    @GetMapping("/accounts")
    public List<BankAccountDto> getBankAccounts()  {
        return  bankAccountService.bankAccountList();

    }

    @GetMapping("/accounts/{id}/operations")
    public List<AccountOperationDto> getBankAccountHistory(@PathVariable(name = "id") String accountId)  {
        return  bankAccountService.accountsHistory(accountId);

    }
    @GetMapping("/accounts/{id}/pageoperations")
    public AccountHistoryDto getAccountHistory(@PathVariable(name = "id") String accountId,
                                               @RequestParam (name = "page",defaultValue = "0")int page,
                                               @RequestParam (name = "size",defaultValue = "5")int size
    ) throws BankAccountNotFoundException {
        return  bankAccountService.getaccountsHistory(accountId,page,size);

    }
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }
}
