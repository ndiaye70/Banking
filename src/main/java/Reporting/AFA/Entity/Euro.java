package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Euro {
    public Long getId() {
        return id;
    }

    public int getBilletCinqCents() {
        return billetCinqCents;
    }

    public void setBilletCinqCents(int billetCinqCents) {
        this.billetCinqCents = billetCinqCents;
    }

    public int getBilletDeuxCents() {
        return billetDeuxCents;
    }

    public void setBilletDeuxCents(int billetDeuxCents) {
        this.billetDeuxCents = billetDeuxCents;
    }

    public int getBilletCent() {
        return billetCent;
    }

    public void setBilletCent(int billetCent) {
        this.billetCent = billetCent;
    }

    public int getBilletCinquante() {
        return billetCinquante;
    }

    public void setBilletCinquante(int billetCinquante) {
        this.billetCinquante = billetCinquante;
    }

    public int getBilletCinq() {
        return billetCinq;
    }

    public void setBilletCinq(int billetCinq) {
        this.billetCinq = billetCinq;
    }

    public int getBilletDix() {
        return billetDix;
    }

    public void setBilletDix(int billetDix) {
        this.billetDix = billetDix;
    }

    public int getBilletVingt() {
        return billetVingt;
    }

    public void setBilletVingt(int billetVingt) {
        this.billetVingt = billetVingt;
    }

    public void setId(Long id) {
        this.id = id;
    }





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public int billetCinqCents;
    public int billetDeuxCents;
    public int billetCent;
    public int billetCinquante;
    public int billetCinq;
    public int billetDix;
    public int billetVingt;

    public Euro() {
        // Le constructeur privé évite l'instanciation de cette classe.
    }



    public double calculerMontantTotal() {
        return billetCinqCents * 500 +
                billetDeuxCents * 200 +
                billetCent * 100 +
                billetCinquante * 50 +
                billetCinq * 5 +
                billetDix * 10 +
                billetVingt * 20;
    }

    // Vous pouvez ajouter d'autres méthodes ou constructeurs si nécessaire
}
