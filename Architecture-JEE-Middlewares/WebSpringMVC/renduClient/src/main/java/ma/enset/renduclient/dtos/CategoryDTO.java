package ma.enset.renduclient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.renduclient.entities.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {
    private  Long id;
    private  String name;
}
//atttention aux dependances cyclique
/*
* ->solution 1: annotation jackson(serialize entité en json)
*
* ->solution 2:Création de DTOS
* */
