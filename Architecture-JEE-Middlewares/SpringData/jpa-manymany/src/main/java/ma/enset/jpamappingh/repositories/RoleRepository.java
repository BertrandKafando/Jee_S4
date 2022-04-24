package ma.enset.jpamappingh.repositories;

import ma.enset.jpamappingh.entities.Role;
import ma.enset.jpamappingh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String role);
}
