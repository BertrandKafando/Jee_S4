package metier;

import dao.IDao;

public class IMetierImpl implements IMetier {
    //interface-classe
    private IDao dao;
    @Override
    public double calcul() {
        return dao.getdata()+1*3;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
