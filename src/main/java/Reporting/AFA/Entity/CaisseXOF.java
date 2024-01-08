package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
public class CaisseXOF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private XOF xof;
    private Date dateCreation;
    @Getter
    private double montantTotal;

    // Ajoutez d'autres attributs si nécessaire

    // Getters et setters

    public CaisseXOF() {
        this.id = 0L;
        this.xof = new XOF();
        this.dateCreation = new Date();
        this.montantTotal = 0.0;
    }

    public CaisseXOF(XOF xof) {
        this.id = 0L;
        this.dateCreation = new Date();
        this.xof = xof;
        if (this.xof != null) {
            this.montantTotal = xof.calculerMontantTotal();
        } else {
            this.montantTotal = 0.0;
        }
    }

    public void calculerMontantTotal() {
        this.montantTotal = xof.calculerMontantTotal();
    }

    public void setBilletDixMille(int quantite) {
        xof.setBilletDixMille(quantite);
    }

    public void setBilletCinqMille(int quantite) {
        xof.setBilletCinqMille(quantite);
    }

    public void setBilletDeuxMille(int quantite) {
        xof.setBilletDeuxMille(quantite);
    }

    public void setBilletMille(int quantite) {
        xof.setBilletMille(quantite);
    }

    public void setBilletCinqCent(int quantite) {
        xof.setBilletCinqCent(quantite);
    }
    public void setPieceCinqCent(int quantite) {
        xof.setPieceCent(quantite);
    }

    public void setPieceDeuxCent(int quantite) {
        xof.setPieceDeuxCent(quantite);
    }

    public void setPieceCent(int quantite) {
        xof.setPieceCent(quantite);
    }

    public void setPieceCinquante(int quantite) {
        xof.setPieceCinquante(quantite);
    }

    public void setPieceVingtCinq(int quantite) {
        xof.setPieceVingtCinq(quantite);
    }

    public void setPieceDix(int quantite) {
        xof.setPieceDix(quantite);
    }

    public void setPieceCinq(int quantite) {
        xof.setPieceCinq(quantite);
    }

}
