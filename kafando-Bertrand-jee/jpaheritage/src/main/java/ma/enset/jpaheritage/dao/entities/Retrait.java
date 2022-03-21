package ma.enset.jpaheritage.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
@Data @AllArgsConstructor @NoArgsConstructor
public class Retrait extends Operation{
}
