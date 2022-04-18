package ma.enset.renduclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.renduclient.entities.Category;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data @AllArgsConstructor @NoArgsConstructor
public class ProductDTO {
    private  String id;
    private String nom;
    private double prix;
    private double quantity;
    private CategoryDTO categoryDTO;
}
