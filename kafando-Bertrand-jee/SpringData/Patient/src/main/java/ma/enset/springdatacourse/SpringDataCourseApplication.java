package ma.enset.springdatacourse;

import ma.enset.springdatacourse.entities.Patient;
import ma.enset.springdatacourse.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class  SpringDataCourseApplication  implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringDataCourseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i<100;i++){
            patientRepository.save(new Patient(null,"bertrand",new Date(),Math.random()>0.5?false:true,(int)(Math.random()*100)));

        }
        //findAll()
        Page<Patient>patients=patientRepository.findAll(PageRequest.of(0,5));
        System.out.println("nombre: "+patients.getTotalElements());
        List<Patient> content=patients.getContent();
        /*
        content.forEach(p->{
            System.out.println("**************");
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.isMalade());
        });



        /////findByMalade
        Page<Patient> bymalade=patientRepository.findByMalade(true,PageRequest.of(0,5));
        bymalade.forEach(p->{
            System.out.println("**************");
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.isMalade());
        });


         */

        List<Patient>patients1=patientRepository.chercherPatients("%t%",50);
        patients1.forEach(p->{
            System.out.println("***************");
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.isMalade());
        });



        System.out.println("/////////////////////");

        Patient patient=patientRepository.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        //patient.setScore(900);
        //patientRepository.save(patient);
        //patientRepository.deleteById(1L);

    }
}
