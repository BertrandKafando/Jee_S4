import metier.IMetier;
import metier.MetierImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class AppJaxb {
    public static void main(String[] args) {
        try {
            JAXBContext context=JAXBContext.newInstance(MetierImpl.class);
            Unmarshaller unmarshaller=context.createUnmarshaller();
             IMetier metier=(IMetier) unmarshaller.unmarshal(new File("src/main/resources/test.xml"));
            System.out.println(metier.calcul());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
