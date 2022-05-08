package ma.enset.apirest;

import ma.enset.apirest.entities.Student;
import ma.enset.apirest.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static ma.enset.apirest.entities.Genre.Feminin;
import static ma.enset.apirest.entities.Genre.Masculin;

@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner (StudentRepository studentRepository){
		return args -> {
			Stream.of("kafando Bertrand","kabore Hassan","sahl Yasmine","diallo Assim").forEach(p->{
				String tab[]=p.split(" ");
			Student	etudiant=new Student();
				etudiant.setName(tab[0]);
				etudiant.setSurname(tab[1]);
				etudiant.setDateNaissance(new Date());
				etudiant.setGenre(Math.random()>0.5? Masculin: Feminin);
				etudiant.setRegle(Math.random()>0.5? true:false);
				studentRepository.save(etudiant);
			});

			List<Student> students=studentRepository.findByNameContains("");
			students.forEach(p->{
				System.out.println(p);
			});

			System.out.println("ok");
		};
	}



}
