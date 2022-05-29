package ma.enset.bank_api.sec.service;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import ma.enset.bank_api.sec.entities.AppRole;
import ma.enset.bank_api.sec.entities.AppUser;
import ma.enset.bank_api.sec.repositories.AppRoleRepository;
import ma.enset.bank_api.sec.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j  //logue
@AllArgsConstructor //injection dep
@Transactional
public class SecurityServiceIImpl implements SecurityServiceI {
    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(AppUser user) {
        String hashedPWD=passwordEncoder.encode(user.getPassword());
        user.setUserId(UUID.randomUUID().toString());//depend de la date système
       user.setPassword(hashedPWD);
        AppUser savedUser= appUserRepository.save(user);
        return savedUser;
    }

    @Override
    public AppRole saveNewRole(AppRole appRole) {
        //tester si le role existe deja
       if(appRoleRepository.findByRolename(appRole.getRolename())!=null) throw new RuntimeException("Role already exist");
       AppRole savedAppRole=appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
        appRole.getAppUsers().add(appUser);
        //transactionnel pour  ajouter automatiquement l'autre sens

    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }

    @Override
    public void removeRoleToUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }

}
