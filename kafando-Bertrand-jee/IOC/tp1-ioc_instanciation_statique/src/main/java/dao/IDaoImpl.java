package dao;

public class IDaoImpl implements IDao {
    @Override
    public double getdata() {
        // return un data aleattoire
        System.out.println("de la db");
        return Math.random()*50;
    }
}
