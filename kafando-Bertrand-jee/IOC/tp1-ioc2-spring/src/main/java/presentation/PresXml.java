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

        ApplicationContext context= new ClassPathXmlApplicationContext("config.xml");

        IMetier metier=(IMetier) context.getBean("metier");

        System.out.println(metier.calcul());



    }
}
