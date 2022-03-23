package ma.enset.jpamappingh.repositories;

import ma.enset.jpamappingh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //--> component  pour dao pas obligatoire
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String userName);

}
