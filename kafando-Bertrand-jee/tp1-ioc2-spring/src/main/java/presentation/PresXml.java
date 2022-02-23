package presentation;

import dao.IDao;
import metier.IMetier;
import metier.IMetierImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class PresXml {
    public static void main(String[] args) throws Exception {
      /*  Scanner scanner=new Scanner(new File("src/main/resources/config.txt"));
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
*/
/*
        ApplicationContext context= new ClassPathXmlApplicationContext("config.xml");
        IMetier metier=(IMetier) context.getBean("metier");

        System.out.println(metier.calcul());
*/

        ApplicationContext context= new AnnotationConfigApplicationContext("dao","metier","ext");
        IMetier metier=(IMetier) context.getBean(IMetier.class);

        System.out.println(metier.calcul());
    }
}
