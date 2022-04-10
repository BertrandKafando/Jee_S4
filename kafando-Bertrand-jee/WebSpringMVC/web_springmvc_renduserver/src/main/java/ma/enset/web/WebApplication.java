package ma.enset.web;

import ma.enset.web.entities.Patient;
import ma.enset.web.repositories.PatientRepository;
import ma.enset.web.sec.repositories.AppRoleRepository;
import ma.enset.web.sec.repositories.AppUserRepository;
import ma.enset.web.sec.service.SecurityServiceI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            Stream.of("Bertrand", "Hassan", "yasmine", "cecile").forEach(p -> {
                patientRepository.save(
                        new Patient(null, p, new Date(), Math.random() > 0.5 ? false : true, 321)
                );
            });

            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getName());
            });

        };

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner saisieUsers(SecurityServiceI securityServiceI) {
        return args -> {
           /* securityServiceI.saveNewUser("Bertrand","1234","1234");
            securityServiceI.saveNewUser("Mohammed","1234","1234");
            securityServiceI.saveNewUser("Yasmine","1234","1234");

            securityServiceI.saveNewRole("USER","");
            securityServiceI.saveNewRole("ADMIN","");
*/
            securityServiceI.addRoleToUser("Bertrand", "User");
            securityServiceI.addRoleToUser("Bertrand", "ADMIN");
            securityServiceI.addRoleToUser("Mohammed", "User");
            securityServiceI.addRoleToUser("Yasmine", "User");

        };
    }

}
