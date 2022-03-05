package metier;

import dao.DaoImpl;
import dao.IDao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MetierImpl implements IMetier,Serializable {

    //@XmlElement(type = DaoImpl.class) // choisir a classe à implementer
    private IDao dao; //statique
    @Override
    public double calcul() {
        return dao.getdata()*2+1;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }


}

