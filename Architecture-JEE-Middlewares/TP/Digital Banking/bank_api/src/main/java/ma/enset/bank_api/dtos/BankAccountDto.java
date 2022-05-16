package ma.enset.bank_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bank_api.entities.AccountOperation;
import ma.enset.bank_api.entities.Customer;
import ma.enset.bank_api.enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public  class BankAccountDto  {
private String type;

    /*
    * si on veut seulement quelques informations je suis tout temps oblié de
    * recuper l,Objet , je peux prendre categorieName seulement ici
    * */

}
