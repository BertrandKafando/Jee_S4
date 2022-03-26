package ma.enset.mappingassocitaionh.repositories;

import ma.enset.mappingassocitaionh.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findAllByNom(String nom);
}
