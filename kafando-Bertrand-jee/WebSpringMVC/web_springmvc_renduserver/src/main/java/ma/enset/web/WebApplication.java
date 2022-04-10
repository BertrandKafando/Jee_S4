package ma.enset.web;

import ma.enset.web.entities.Patient;
import ma.enset.web.repositories.PatientRepository;
import ma.enset.web.sec.repositories.AppRoleRepository;
import ma.enset.web.sec.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
//@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            Stream.of("Bertrand","Hassan","yasmine","cecile").forEach(p->{
                patientRepository.save(
                        new Patient(null,p,new Date(),Math.random()>0.5?false:true,321)
                );
            });

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });

        };

    }
    @Bean
    CommandLineRunner saisieUsers(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository){
        return args -> {

        };
    }
}
