package ma.enset.renduclient.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    private  String id;
    private String nom;
    private double prix;
    private double quantity;
    @ManyToOne
    private Category category;
}
