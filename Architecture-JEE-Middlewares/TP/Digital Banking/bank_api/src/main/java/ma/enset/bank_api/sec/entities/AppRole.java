package ma.enset.bank_api.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long roleId;
    @Column(unique = true)
    private String rolename;
    private String description;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    List<AppUser>appUsers=new ArrayList<>();

}
