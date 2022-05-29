package ma.enset.bank_api.sec.service;

import ma.enset.bank_api.sec.entities.AppRole;
import ma.enset.bank_api.sec.entities.AppUser;

import java.util.List;

public interface SecurityServiceI {
    AppUser saveNewUser(AppUser user);
    AppRole saveNewRole(AppRole role);
    void addRoleToUser(String username,String rolename);

    AppUser loadUserByUsername(String username);
    void removeRoleToUser(String username,String rolename);

    List<AppUser> listUsers();
}
