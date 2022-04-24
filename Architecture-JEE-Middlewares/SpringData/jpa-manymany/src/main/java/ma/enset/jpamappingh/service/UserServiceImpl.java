package ma.enset.jpamappingh.service;

import lombok.AllArgsConstructor;
import ma.enset.jpamappingh.entities.Role;
import ma.enset.jpamappingh.entities.User;
import ma.enset.jpamappingh.repositories.RoleRepository;
import ma.enset.jpamappingh.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service  //-->remplace componnent pour Ij depen
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    /*
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    ou AllArgsConstructor de lambock
    */

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findByRoleName(String rolename) {
        return roleRepository.findByRoleName(rolename);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user= userRepository.findByUsername(username);
        Role role= roleRepository.findByRoleName(rolename);
        if(user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
            // normalement pas besoin jpa s'en charge
        }

        //pas besoin  car transactionnel ,le commit est fait automatique
        userRepository.save(user);

    }

    @Override
    public User authenticate(String username, String password) {
        User user= userRepository.findByUsername(username);
        if(user==null) throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
