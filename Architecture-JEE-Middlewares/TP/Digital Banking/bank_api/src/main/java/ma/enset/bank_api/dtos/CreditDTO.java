package ma.enset.bank_api.dtos;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private  double amount;
    private  String description;
}
