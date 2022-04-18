package ma.enset.apspringetudiant.Web;

import lombok.AllArgsConstructor;
import ma.enset.apspringetudiant.entities.Etudiant;
import ma.enset.apspringetudiant.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

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

    @RequestMapping("/delete/{id}")  //au lieu de delete
    public String delete(@PathVariable("id")  Long ide,String name,int page){
        etudiantRepository.deleteById(ide);
        return "redirect:/index?page="+page+"&name="+name;
    }

    @GetMapping("/formEtudiant")
    public String formEtudiant(Model model){
        Etudiant etudiant=new Etudiant();
        model.addAttribute("Genres", Arrays.asList(etudiant.getGenre().values()));
        model.addAttribute("etudiant",etudiant);
        return "formEtudiant";
    }

    @GetMapping("/")
    String home(){
        return "home";
    }


    @PostMapping("/save")
    public  String save(Model model , @Valid Etudiant etudiant, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formEtudiant";
        etudiantRepository.save(etudiant);
        return "redirect:/index";
    }

    @GetMapping(path="/edit")
    public String edit(Model model,Long id,String name,int page){
        Etudiant etudiant=etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new RuntimeException("etudiant introuvable");
        model.addAttribute("Genres", Arrays.asList(etudiant.getGenre().values()));
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",name);
        return "editEtudiant";
    }

    @PostMapping(path = "/saveedit")
    public String save(Model model , @Valid Etudiant etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0")   int page ,
                       @RequestParam(defaultValue = "")  String keyword){
        if(bindingResult.hasErrors()) return "editEtudiant";
        etudiantRepository.save(etudiant);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }


    //etudiants en regle

    @GetMapping( "/regle")
    String etudiantsregle(Model model,
                     @RequestParam(name = "page",defaultValue = "0") int page,
                     @RequestParam(name = "size",defaultValue = "4" ) int size){
        Page<Etudiant> etudiants=etudiantRepository.findEtudiantByRegleTrue(PageRequest.of(page,size));
        model.addAttribute("etudiants",etudiants);
        model.addAttribute("pages",new int[etudiants.getTotalPages()]);
        model.addAttribute("curentPage",page);
        return "etudiantsRegle";
    }


}
