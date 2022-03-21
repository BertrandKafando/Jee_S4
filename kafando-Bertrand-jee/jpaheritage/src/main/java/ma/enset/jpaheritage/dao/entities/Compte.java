package ma.enset.jpaheritage.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE",discriminatorType = DiscriminatorType.STRING,length = 2)

public class Compte {
    @Id
    private String numCompte;
    @Temporal(TemporalType.DATE)
    private Date  dateCreation;
    private double solde;
    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "compte")
    private List<Operation>operations;


}
