package ma.enset.web.sec.repositories;

import ma.enset.web.sec.entities.AppRole;
import ma.enset.web.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
 AppRole findByRolename(String roleName);
}
