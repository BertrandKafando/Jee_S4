package ma.enset.apspringetudiant.Web;


import lombok.AllArgsConstructor;
import ma.enset.apspringetudiant.dtos.EtudiantDto;
import ma.enset.apspringetudiant.entities.Etudiant;
import ma.enset.apspringetudiant.mappers.EtudiantMapper;
import ma.enset.apspringetudiant.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {
    EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;

    @GetMapping( "/etudiants")
    List<Etudiant> etudiants(@RequestParam(name = "name",defaultValue = "") String name){
        List<Etudiant> etudiants=etudiantRepository.findByNameContains(name);

        return etudiants;
    }
    @RequestMapping("/delete/{id}")  //au lieu de delete
    public void  delete(@PathVariable("id")  Long ide){
        etudiantRepository.deleteById(ide);
    }
    @PostMapping("/add")
    public  Etudiant save( @RequestBody Etudiant etudiant){
        Etudiant etudiant1= etudiantRepository.save(etudiant);
        return etudiant1;
    }


}
