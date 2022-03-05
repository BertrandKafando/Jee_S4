package dao;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class DaoImpl implements IDao, Serializable {
    @Override
    public double getdata() {
        double temp=Math.random()*40;
        return temp;
    }

}
