package ma.enset.bank_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bank_api.entities.BankAccount;
import ma.enset.bank_api.enums.OperationType;

import javax.persistence.*;
import java.util.Date;


@Data
public class AccountOperationDto {
    private Long id;
    Date operationDate;
    private  double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private OperationType type;
}
