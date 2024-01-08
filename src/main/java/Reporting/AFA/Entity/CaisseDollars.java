package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class CaisseDollars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreation;

    @OneToOne
    private Dollars dollars;

    private double montantTotal;

    public CaisseDollars() {
        this.id = 0L;
        this.dateCreation = new Date();
        this.dollars = new Dollars();
        this.montantTotal = 0.0;
    }

    public CaisseDollars(Dollars dollars) {
        this.id = 0L;
        this.dateCreation = new Date();
        this.dollars = dollars;
        if (this.dollars != null) {
            this.montantTotal = dollars.calculerMontantTotal();
        } else {
            this.montantTotal = 0.0;
        }
    }

    public void calculerMontantTotal() {
        montantTotal = dollars.calculerMontantTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Dollars getDollars() {
        return dollars;
    }

    public void setDollars(Dollars dollars) {
        this.dollars = dollars;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setBilletCinqDollars(int quantite) {
        dollars.setBilletCinqDollars(quantite);
    }

    public void setBilletDixDollars(int quantite) {
        dollars.setBilletDixDollars(quantite);
    }

    public void setBilletVingtDollars(int quantite) {
        dollars.setBilletVingtDollars(quantite);
    }

    public void setBilletCinquanteDollars(int quantite) {
        dollars.setBilletCinquanteDollars(quantite);
    }

    public void setBilletCentDollars(int quantite) {
        dollars.setBilletCentDollars(quantite);
    }


}

