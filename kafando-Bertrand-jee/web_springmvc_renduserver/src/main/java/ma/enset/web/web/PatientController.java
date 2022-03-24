package ma.enset.web.web;

import lombok.AllArgsConstructor;
import ma.enset.web.entities.Patient;
import ma.enset.web.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
 @AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String patients(Model model){
        //traitements
        List<Patient>patients=patientRepository.findAll();
        //stocker dans le model
        model.addAttribute("listePatient",patients);
        //retouner la vue aus servlet
        return "patients";
    }
}
