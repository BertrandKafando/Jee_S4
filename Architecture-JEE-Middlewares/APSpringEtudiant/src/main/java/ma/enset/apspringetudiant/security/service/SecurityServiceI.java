package ma.enset.apspringetudiant.security.service;

import ma.enset.apspringetudiant.security.entities.AppRole;
import ma.enset.apspringetudiant.security.entities.AppUser;

public interface SecurityServiceI {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String rolename, String description);
    void addRoleToUser(String username,String rolename);

    AppUser loadUserByUsername(String username);
    void removeRoleToUser(String username,String rolename);

}
