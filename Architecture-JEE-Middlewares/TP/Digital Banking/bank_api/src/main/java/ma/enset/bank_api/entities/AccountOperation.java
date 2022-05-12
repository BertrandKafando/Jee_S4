package ma.enset.bank_api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bank_api.enums.OperationType;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Date operationDate;
    private  double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
     @ManyToOne()
    private BankAccount bankAccount;
}
