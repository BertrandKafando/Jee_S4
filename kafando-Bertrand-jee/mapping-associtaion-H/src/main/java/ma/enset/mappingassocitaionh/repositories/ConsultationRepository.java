package ma.enset.mappingassocitaionh.repositories;

import ma.enset.mappingassocitaionh.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
