package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component ("capteur")
public class Daoimpl implements IDao {
    @Override
    public double getdata() {
        System.out.println("version capteurs");
        return Math.random()*30-2;
    }
}
