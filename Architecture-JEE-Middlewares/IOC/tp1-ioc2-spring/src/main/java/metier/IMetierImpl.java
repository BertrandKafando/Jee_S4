package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IMetierImpl implements IMetier {
    //interface-classe
     @Autowired
    @Qualifier("bd")
    private IDao dao;
    @Override
    public double calcul() {
        return dao.getdata()+1*3;
    }

   /* public void setDao(IDao dao) {
        this.dao = dao;
    }*/

    /*public IMetierImpl(IDao dao) {
        this.dao = dao;
    }*/
}
