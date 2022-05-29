package ma.enset.bank_api.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean active;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    List<AppRole>appRoles=new ArrayList<>();
}
