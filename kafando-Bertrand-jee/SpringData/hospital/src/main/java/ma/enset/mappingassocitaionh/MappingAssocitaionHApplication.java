package ma.enset.mappingassocitaionh;

import ma.enset.mappingassocitaionh.entities.*;
import ma.enset.mappingassocitaionh.repositories.ConsultationRepository;
import ma.enset.mappingassocitaionh.repositories.MedecinRepository;
import ma.enset.mappingassocitaionh.repositories.PatientRepository;
import ma.enset.mappingassocitaionh.repositories.RendezVousRepository;
import ma.enset.mappingassocitaionh.service.HospitalImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class MappingAssocitaionHApplication {

    public static void main(String[] args) {
        SpringApplication.run(MappingAssocitaionHApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository,
                            ConsultationRepository consultationRepository,
                            HospitalImp hospitalImp){
        return args -> {
            Stream.of("Bertand","Mohammed","Hassan")
                    .forEach(name->{
                        Patient patient=new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        patientRepository.save(patient);
                    });

            Stream.of("Cecile","Naoufal","yasm")
                    .forEach(name->{
                        Medecin medecin=new Medecin();
                        medecin.setNom(name);
                        medecin.setSpecialite(Math.random()>0.5?"cardio":"dentiste");
                        medecin.setEmail(name+"@gmail.com");
                        medecinRepository.save(medecin);
                    });

            Medecin medecin=medecinRepository.findById(1l).orElse(null);
            Patient patient=patientRepository.findById(1l).orElse(null);
            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            hospitalImp.saveRendezVous(rendezVous);



            RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
            Consultation consultation=new Consultation();
            consultation.setDateConsultation(rendezVous1.getDate());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("rapport ..........");
            consultationRepository.save(consultation);


        };
    }
}
