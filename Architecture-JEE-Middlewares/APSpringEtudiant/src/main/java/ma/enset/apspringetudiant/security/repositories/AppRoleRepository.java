package ma.enset.apspringetudiant.security.repositories;

import ma.enset.apspringetudiant.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
 AppRole findByRolename(String roleName);
}
