package ma.enset.jpaheritage.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
@Data @AllArgsConstructor @NoArgsConstructor
public class CompteEpargne extends Compte{
    private double taux;
}
