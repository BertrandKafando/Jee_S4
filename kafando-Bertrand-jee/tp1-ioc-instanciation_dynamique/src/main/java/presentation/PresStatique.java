package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class PresStatique {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(new File("src/main/resources/config.txt"));
        String daoclassname=scanner.nextLine();
        String metierclassname=scanner.nextLine();

        Class Dao=Class.forName(daoclassname);
        Class Metier=Class.forName(metierclassname);

        IDao dao=(IDao) Dao.newInstance();
        IMetier metier= (IMetier) Metier.newInstance();

        Method method=Metier.getMethod("setDao",IDao.class);
        //metier.setDao(dao)
        method.invoke(metier,dao);

        System.out.println(metier.calcul());





    }
}
