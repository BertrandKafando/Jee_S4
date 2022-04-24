package ma.enset.apspringetudiant.Web;

import lombok.AllArgsConstructor;
import ma.enset.apspringetudiant.repositories.EtudiantRepository;
import ma.enset.apspringetudiant.security.entities.AppRole;
import ma.enset.apspringetudiant.security.entities.AppUser;
import ma.enset.apspringetudiant.security.repositories.AppRoleRepository;
import ma.enset.apspringetudiant.security.repositories.AppUserRepository;
import ma.enset.apspringetudiant.security.service.SecurityServiceI;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class SecurityController {
   AppUserRepository appUserRepository;
   AppRoleRepository appRoleRepository;
   SecurityServiceI securityServiceI;
   PasswordEncoder passwordEncoder;
    @GetMapping("/403")
    public String notAuthorized(){
        return "403";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        AppUser appUser=new AppUser();
        model.addAttribute("user",appUser);
        return  "registration";
    }

    @PostMapping("/savereg")
    public  String saveregistration (Model model, @Valid AppUser user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "registration";
        user.setUserId(UUID.randomUUID().toString());
        user.getAppRoles().add(appRoleRepository.findByRolename("USER"));
        String hashedPWD=passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPWD);
        appUserRepository.save(user);
        return "registersucess";
    }
}
