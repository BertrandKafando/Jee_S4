package ma.enset.renduclient.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;
    @OneToMany(mappedBy = "category")
            @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //annotation de JACkSON
    List<Product>products=new ArrayList<>();
}
//atttention aux dependances cyclique
/*
* ->solution 1: annotation jackson(serialize entité en json)
*
* ->solution 2:Création de DTOS
* */
