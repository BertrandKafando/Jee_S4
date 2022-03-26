package ma.enset.jpamappingh.service;

import ma.enset.jpamappingh.entities.Role;
import ma.enset.jpamappingh.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findByRoleName(String rolename);
    void addRoleToUser(String user,String role);
    User authenticate(String username,String password);
}
