package ma.enset.mappingassocitaionh.service;

import ma.enset.mappingassocitaionh.entities.Consultation;
import ma.enset.mappingassocitaionh.entities.Medecin;
import ma.enset.mappingassocitaionh.entities.Patient;
import ma.enset.mappingassocitaionh.entities.RendezVous;

public interface HospitalImp {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    Consultation saveConsultation(Consultation consultation);
    RendezVous saveRendezVous(RendezVous rendezVous);
}
