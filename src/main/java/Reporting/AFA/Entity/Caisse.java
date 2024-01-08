package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreation;

   // private String nature ;

    @OneToOne
    private Euro euros;

    @Getter
    private double montantTotal;

    public Caisse() {
        this.id = 0L; // Vous pouvez définir l'ID de manière appropriée
        this.dateCreation = new Date();
        this.euros = new Euro();
        this.montantTotal = 0.0;
    }

    public Caisse(Euro euros) {
        this.id = 0L; // Vous pouvez définir l'ID de manière appropriée
        this.dateCreation = new Date();
        this.euros = euros;
        if(this.euros!=null) {
            this.montantTotal = euros.calculerMontantTotal();
        }
        else {
            this.montantTotal = 0.0;
        }
    }

    public void calculerMontantTotal() {
        montantTotal = euros.calculerMontantTotal();
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

    public Euro getEuros() {
        return euros;
    }

    public void setEuros(Euro euros) {
        this.euros = euros;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setBilletCinqCents(int quantite) {
        euros.setBilletCinqCents(quantite);
    }

    public void setBilletDeuxCents(int quantite) {
        euros.setBilletDeuxCents(quantite);
    }

    public void setBilletCent(int quantite) {
        euros.setBilletCent(quantite);
    }

    public void setBilletCinquante(int quantite) {
        euros.setBilletCinquante(quantite);
    }

    public void setBilletCinq(int quantite) {
        euros.setBilletCinq(quantite);
    }

    public void setBilletDix(int quantite) {
        euros.setBilletDix(quantite);
    }

    public void setBilletVingt(int quantite) {
        euros.setBilletVingt(quantite);
    }
}
