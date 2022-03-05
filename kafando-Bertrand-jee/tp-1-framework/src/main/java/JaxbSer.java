import dao.DaoImpl;
import dao.IDao;
import metier.IMetier;
import metier.MetierImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class JaxbSer {
    public static void main(String[] args) {
        try {

            DaoImpl dao =new DaoImpl();
            MetierImpl metier=new MetierImpl();metier.setDao(dao);

            JAXBContext context=JAXBContext.newInstance(metier.getClass());
            Marshaller marshaller=context.createMarshaller();
            marshaller.marshal(metier,new File("textse.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

