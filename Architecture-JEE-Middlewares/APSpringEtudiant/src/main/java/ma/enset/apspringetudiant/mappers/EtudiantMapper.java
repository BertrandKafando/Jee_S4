package ma.enset.apspringetudiant.mappers;

import ma.enset.apspringetudiant.dtos.EtudiantDto;
import ma.enset.apspringetudiant.entities.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EtudiantMapper {

    public EtudiantDto fromEtudiant(Etudiant etudiant){
        EtudiantDto etudiantDto=new EtudiantDto();
        BeanUtils.copyProperties(etudiant,etudiantDto);
        return  etudiantDto;
    }

    public Etudiant fromEtudiantdo(EtudiantDto etudiantdto){
        Etudiant etudiant=new Etudiant();
        BeanUtils.copyProperties(etudiantdto,etudiant);
        return  etudiant;
    }
}
