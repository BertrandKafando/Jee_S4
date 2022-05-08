package ma.enset.apirest.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.apirest.entities.Genre;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentDto implements Serializable {
    private  Long id;
    private String name;
    private String surname;
    private Date  dateNaissance;
    private Genre genre;
    private boolean regle;

}
