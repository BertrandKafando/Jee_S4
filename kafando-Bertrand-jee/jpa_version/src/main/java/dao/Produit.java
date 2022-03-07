package dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PRODUIT")
public class Produit implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ref")
        private  Long reference;
        @Column(name = "DES")
        private String designation;
        @Column()
        private double prix;
        @Column
        private int quantite;

        //getters et setters

        public Long getReference() {
                return reference;
        }

        public void setReference(Long reference) {
                this.reference = reference;
        }

        public String getDesignation() {
                return designation;
        }

        public void setDesignation(String designation) {
                this.designation = designation;
        }

        public double getPrix() {
                return prix;
        }

        public void setPrix(double prix) {
                this.prix = prix;
        }

        public int getQuantite() {
                return quantite;
        }

        public void setQuantite(int quantite) {
                this.quantite = quantite;
        }
}
