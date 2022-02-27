package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresAnn {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext("dao","metier","ext");
        IMetier metier=(IMetier) context.getBean(IMetier.class);

        System.out.println(metier.calcul());
    }
}
