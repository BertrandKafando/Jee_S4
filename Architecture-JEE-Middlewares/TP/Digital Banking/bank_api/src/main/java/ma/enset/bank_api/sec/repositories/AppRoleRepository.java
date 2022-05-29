package ma.enset.bank_api.sec.repositories;

import ma.enset.bank_api.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
 AppRole findByRolename(String roleName);
}
