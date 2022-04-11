package ma.enset.apspringetudiant.repositories;

import ma.enset.apspringetudiant.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Page<Etudiant>findByNameContains(String name, Pageable pageable);
}
