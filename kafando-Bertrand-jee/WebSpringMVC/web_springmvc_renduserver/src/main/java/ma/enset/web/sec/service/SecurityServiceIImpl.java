package ma.enset.web.sec.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.enset.web.sec.entities.AppRole;
import ma.enset.web.sec.entities.AppUser;
import ma.enset.web.sec.repositories.AppRoleRepository;
import ma.enset.web.sec.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new  RuntimeException("Password not match");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());//depend de la date système
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        AppUser savedUser= appUserRepository.save(appUser);
        return savedUser;
    }

    @Override
    public AppRole saveNewRole(String rolename, String description) {
        //tester si le role existe deja
       if(appRoleRepository.findByRolename(rolename)!=null) throw new RuntimeException("Role already exist");
        AppRole appRole=new AppRole();
       appRole.setRolename(rolename);
       appRole.setDescription(description);
       AppRole savedAppRole=appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole); //transactionnel pour  ajouter automatiquement l'autre sens

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
}
