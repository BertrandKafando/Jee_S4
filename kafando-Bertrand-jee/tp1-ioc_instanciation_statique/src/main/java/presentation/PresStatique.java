package presentation;

import dao.IDao;
import dao.IDaoImpl;
import metier.IMetier;
import metier.IMetierImpl;

public class PresStatique {
    public static void main(String[] args) {
        //IDaoImpl dao=new IDaoImpl();
        IMetierImpl metier=new IMetierImpl();
        System.out.println(metier.calcul());
    }
}
