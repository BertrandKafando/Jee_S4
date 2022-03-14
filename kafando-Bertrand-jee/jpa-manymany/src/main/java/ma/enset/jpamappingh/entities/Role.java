package ma.enset.jpamappingh.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20,unique = true)
    private String roleName;
    @ToString.Exclude
    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User>users;
}
