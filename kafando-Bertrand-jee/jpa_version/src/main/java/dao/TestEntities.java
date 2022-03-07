package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestEntities {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("UP_CAT");
    }
}
