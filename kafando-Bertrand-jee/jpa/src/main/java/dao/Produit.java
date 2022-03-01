package dao;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "PRODUITS")
public class Produit implements Serializable {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REF")
    private  Long reference;
   @Column(name = "DES")
    private  String designation;
    private double prix;
    private  int quantite;
}
