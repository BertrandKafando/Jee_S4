package ma.enset.apspringetudiant;

import ma.enset.apspringetudiant.entities.Etudiant;
import ma.enset.apspringetudiant.entities.Genre;
import ma.enset.apspringetudiant.repositories.EtudiantRepository;
import ma.enset.apspringetudiant.security.service.SecurityServiceI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

import static ma.enset.apspringetudiant.entities.Genre.Feminin;
import static ma.enset.apspringetudiant.entities.Genre.Masculin;

@SpringBootApplication
@ComponentScan({"ma.enset"})
public class ApSpringEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApSpringEtudiantApplication.class, args);
    }
   // @Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository){
        return  args -> {
            Stream.of("kafando Bertrand","kabore Hassan","sahl Yasmine","diallo Assim").forEach(p->{
                String tab[]=p.split(" ");
                Etudiant etudiant=new Etudiant();
                etudiant.setName(tab[0]);
                etudiant.setSurname(tab[1]);
                etudiant.setDateNaissance(new Date());
                etudiant.setGenre(Math.random()>0.5? Masculin: Feminin);
                etudiant.setRegle(Math.random()>0.5? true:false);
                etudiantRepository.save(etudiant);
            });

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   // @Bean
    CommandLineRunner saisieUsers(SecurityServiceI securityServiceI) {
        return args -> {
          securityServiceI.saveNewUser("Bertrand","1234","1234");
            securityServiceI.saveNewUser("Mohammed","1234","1234");
            securityServiceI.saveNewUser("Yasmine","1234","1234");

            securityServiceI.saveNewRole("USER","");
            securityServiceI.saveNewRole("ADMIN","");

            securityServiceI.addRoleToUser("Bertrand", "User");
            securityServiceI.addRoleToUser("Bertrand", "ADMIN");
            securityServiceI.addRoleToUser("Mohammed", "User");
            securityServiceI.addRoleToUser("Yasmine", "User");

        };
    }


}
