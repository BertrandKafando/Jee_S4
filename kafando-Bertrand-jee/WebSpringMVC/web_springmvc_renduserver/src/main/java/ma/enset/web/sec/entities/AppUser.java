package ma.enset.web.sec.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    List<AppRole>appRoles=new ArrayList<>();
}
