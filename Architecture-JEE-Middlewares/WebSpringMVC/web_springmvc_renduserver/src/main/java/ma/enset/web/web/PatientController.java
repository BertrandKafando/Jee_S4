package ma.enset.web.web;

import lombok.AllArgsConstructor;
import ma.enset.web.entities.Patient;
import ma.enset.web.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
        return "home";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient>patientList(){
        return patientRepository.findAll();
    }

@GetMapping(path="/formPatients")
    public String fromPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/save")
    public String save(Model model , @Valid Patient patient, BindingResult bindingResult,
                    @RequestParam(defaultValue = "0")   int page ,
                     @RequestParam(defaultValue = "")  String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path="/edit")
    public String edit(Model model,Long id,String keyword,int page){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "edit";
    }


}
