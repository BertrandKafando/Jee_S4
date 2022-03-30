package ma.enset.web.web;

import lombok.AllArgsConstructor;
import ma.enset.web.entities.Patient;
import ma.enset.web.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String patients(Model model,
                       @RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "size",defaultValue = "4")    int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword){
        //traitements
        Page<Patient> pagepatients=patientRepository.findByNameContains(keyword, PageRequest.of(page,size));
        //stocker dans le model
        model.addAttribute("listePatient",pagepatients);
        model.addAttribute("pages",new int[pagepatients.getTotalPages()]);
        model.addAttribute("curentPage",page);
        model.addAttribute("keyword",keyword);
        //retouner la vue aus servlet
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String hom(){
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient>patientList(){
        return patientRepository.findAll();
    }








}
