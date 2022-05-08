package ma.enset.apspringetudiant.dtos;
import ma.enset.apspringetudiant.entities.Genre;

import java.io.Serializable;
import java.util.Date;
public class EtudiantDto implements Serializable {
    private  Long id;
    private String name;
    private String surname;
    private Date  dateNaissance;
    private Genre genre;
    private boolean regle;

}
