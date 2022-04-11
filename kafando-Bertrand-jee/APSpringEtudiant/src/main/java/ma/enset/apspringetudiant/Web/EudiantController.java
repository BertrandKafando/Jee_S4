package ma.enset.apspringetudiant.Web;

import lombok.AllArgsConstructor;
import ma.enset.apspringetudiant.entities.Etudiant;
import ma.enset.apspringetudiant.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.util.EnumUtils;

/*
L'application doit offrir les fonctionnalités suivantes :
  - Chercher des étudiants par nom
  - Faire la pagination
  - Supprimer des étudiants en utilisant la méthode (DELETE au lieu de GET)
  - Saisir et Ajouter des étudiants avec validation des formulaires
  - Editer et mettre à jour des étudiants
  - Créer une page template
  - Sécuriser l'accès à l'application avec un système d'authentification basé sur Spring security en utilisant la stratégie UseDetails Service
 - Ajouter d'autres fonctionnalités supplémentaires
 */

@Controller
@AllArgsConstructor
public class EudiantController {
    EtudiantRepository etudiantRepository;
    @GetMapping( "/index")
    String etudiants(Model model,
                     @RequestParam(name = "page",defaultValue = "0") int page,
                     @RequestParam(name = "size",defaultValue = "4" ) int size,
                     @RequestParam(name = "name",defaultValue = "") String name){
        Page<Etudiant> etudiants=etudiantRepository.findByNameContains(name, PageRequest.of(page,size));
        model.addAttribute("etudiants",etudiants);
        model.addAttribute("name",name);
        model.addAttribute("pages",new int[etudiants.getTotalPages()]);
        model.addAttribute("curentPage",page);

        return "etudiants";
    }

    @DeleteMapping("/delete")
    public  String delete(Long id,String name,int page){
        etudiantRepository.deleteById(id);
        return "redirect:/index?page="+page+"&name="+name;

    }

    @GetMapping("/formEtudiant")
    public String formEtudiant(Model model){
        Etudiant etudiant=new Etudiant();
        model.addAttribute("etudiant",etudiant);
        return "formEtudiant";
    }

    @GetMapping("/")
    String home(){
        return "home";
    }

}
