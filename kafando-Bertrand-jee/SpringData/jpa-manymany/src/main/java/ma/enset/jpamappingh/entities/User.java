package ma.enset.jpamappingh.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "USERS")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    private  String userId;
     @Column(unique = true,length = 20)
    private  String username;
    private String password;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Role>roles=new ArrayList<>();      //eviter un null pointeur exception
}
