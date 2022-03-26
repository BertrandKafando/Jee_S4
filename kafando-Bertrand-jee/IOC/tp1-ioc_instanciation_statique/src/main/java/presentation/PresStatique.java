package presentation;

import dao.IDao;
import dao.IDaoImpl;
import ext.Daoimpl;
import metier.IMetier;
import metier.IMetierImpl;

public class PresStatique {
    public static void main(String[] args) {
       // IDaoImpl dao=new IDaoImpl();
        Daoimpl dao=new Daoimpl(); //extension
        IMetierImpl metier=new IMetierImpl();
        metier.setDao(dao);
        System.out.println(metier.calcul());
    }
}
