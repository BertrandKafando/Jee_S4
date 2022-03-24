package ma.enset.web.repositories;

import ma.enset.web.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
