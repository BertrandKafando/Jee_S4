package ma.enset.bank_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bank_api.entities.BankAccount;

import javax.persistence.*;
import java.util.List;


@Data
public class CustomerDto {
    private Long Id ;
    private String name;
    private String email;

    //private List<BankAccount>bankAccounts;
}
