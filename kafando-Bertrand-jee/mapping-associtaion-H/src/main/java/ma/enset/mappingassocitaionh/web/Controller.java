package ma.enset.mappingassocitaionh.web;

import lombok.AllArgsConstructor;
import ma.enset.mappingassocitaionh.entities.Patient;
import ma.enset.mappingassocitaionh.service.HospitalImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    HospitalImp hospitalImp;
    @GetMapping("/patients")
    public List<Patient> patient(){
        List<Patient>patients=hospitalImp.getPatienst();
        return patients;
    }
}
