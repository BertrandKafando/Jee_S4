package ma.enset.bank_api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;
    private String name;
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<BankAccount>bankAccounts;
}
