package ma.enset.jpaheritage.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP",discriminatorType = DiscriminatorType.STRING,length = 2)
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numOperation;
    @Temporal(TemporalType.DATE)
    private Date dateOperation;

    @ManyToOne
    private Compte compte;

}
