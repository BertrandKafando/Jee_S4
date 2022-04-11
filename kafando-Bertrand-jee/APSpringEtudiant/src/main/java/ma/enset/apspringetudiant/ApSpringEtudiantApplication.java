package ma.enset.apspringetudiant;

import ma.enset.apspringetudiant.entities.Etudiant;
import ma.enset.apspringetudiant.entities.Genre;
import ma.enset.apspringetudiant.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

import static ma.enset.apspringetudiant.entities.Genre.Feminin;
import static ma.enset.apspringetudiant.entities.Genre.Masculin;

@SpringBootApplication
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

}
