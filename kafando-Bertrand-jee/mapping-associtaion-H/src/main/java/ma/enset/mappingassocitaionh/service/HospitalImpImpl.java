package ma.enset.mappingassocitaionh.service;

import jdk.nashorn.internal.runtime.URIUtils;
import ma.enset.mappingassocitaionh.entities.Consultation;
import ma.enset.mappingassocitaionh.entities.Medecin;
import ma.enset.mappingassocitaionh.entities.Patient;
import ma.enset.mappingassocitaionh.entities.RendezVous;
import ma.enset.mappingassocitaionh.repositories.ConsultationRepository;
import ma.enset.mappingassocitaionh.repositories.MedecinRepository;
import ma.enset.mappingassocitaionh.repositories.PatientRepository;
import ma.enset.mappingassocitaionh.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional

public class HospitalImpImpl implements HospitalImp {
    //injection de dependances
    public HospitalImpImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, ConsultationRepository consultationRepository, RendezVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
       rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }
}
