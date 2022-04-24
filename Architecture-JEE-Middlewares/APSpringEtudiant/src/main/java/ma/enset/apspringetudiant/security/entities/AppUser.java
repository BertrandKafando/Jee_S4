package ma.enset.apspringetudiant.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    List<AppRole>appRoles=new ArrayList<>();
}
