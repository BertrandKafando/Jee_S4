package ma.enset.web.sec.entities;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(unique = true)
    private String rolenamee;
    private String description;
    @ManyToMany
    List<AppUser>appUsers=new ArrayList<>();

}
