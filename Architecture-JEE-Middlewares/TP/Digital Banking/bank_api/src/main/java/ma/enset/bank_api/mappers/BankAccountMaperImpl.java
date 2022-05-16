package ma.enset.bank_api.mappers;

import ma.enset.bank_api.dtos.AccountOperationDto;
import ma.enset.bank_api.dtos.CurrentAccountDto;
import ma.enset.bank_api.dtos.CustomerDto;
import ma.enset.bank_api.dtos.SavingAccountDto;
import ma.enset.bank_api.entities.AccountOperation;
import ma.enset.bank_api.entities.CurrentAccount;
import ma.enset.bank_api.entities.Customer;
import ma.enset.bank_api.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMaperImpl {

    public CustomerDto fromcutomer(Customer customer){
         CustomerDto customerDto=new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }

    public Customer fromcutomerDto(CustomerDto customerDto){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        return customer;
    }


    public SavingAccountDto fromSavingAccount(SavingAccount savingAccount){
         SavingAccountDto savingAccountDto=new SavingAccountDto();
         BeanUtils.copyProperties(savingAccount,savingAccountDto);
         //attributs Customer
        savingAccountDto.setType(savingAccount.getClass().getSimpleName());
        savingAccountDto.setCustomer(fromcutomer(savingAccount.getCustomer()));

        return savingAccountDto;
    }

    public SavingAccount fromSavingAccountDto(SavingAccountDto savingAccountDto){
                SavingAccount savingAccount=new SavingAccount();
                BeanUtils.copyProperties(savingAccountDto,savingAccount);
                savingAccount.setCustomer(fromcutomerDto(savingAccountDto.getCustomer()));
                return savingAccount;
    }


    public CurrentAccountDto fromCurrentAccount(CurrentAccount currentAccount){
            CurrentAccountDto currentAccountDto=new CurrentAccountDto();
            BeanUtils.copyProperties(currentAccount,currentAccountDto);
            currentAccountDto.setCustomer(fromcutomer(currentAccount.getCustomer()));
            currentAccountDto.setType(currentAccount.getClass().getSimpleName());
            return currentAccountDto;
    }
    public CurrentAccount fromCurrentAccountDto(CurrentAccountDto currentAccountDto){
            CurrentAccount currentAccount=new CurrentAccount();
            BeanUtils.copyProperties(currentAccountDto,currentAccount);
            currentAccount.setCustomer(fromcutomerDto(currentAccountDto.getCustomer()));
            return  currentAccount;
    }


    public AccountOperationDto fromAccountOperation(AccountOperation accountOperation){
            AccountOperationDto accountOperationDto=new AccountOperationDto();
            BeanUtils.copyProperties(accountOperation,accountOperationDto);
            return  accountOperationDto;
    }

    public AccountOperation fromAccountOperationDto(AccountOperationDto accountOperationDto){
        AccountOperation accountOperation=new AccountOperation();
        BeanUtils.copyProperties(accountOperationDto,accountOperation);
        return  accountOperation;

    }


}
