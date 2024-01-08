package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Dollars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public int billetUnDollar;
    public int billetDeuxDollars;
    public int billetCinqDollars;
    public int billetDixDollars;
    public int billetVingtDollars;
    public int billetCinquanteDollars;
    public int billetCentDollars;

    public Dollars() {
        // Le constructeur privé évite l'instanciation de cette classe.
    }

    public double getValeur(String typeDollar) {
        switch (typeDollar) {
            case "billetUnDollar":
                return 1;
            case "billetDeuxDollars":
                return 2;
            case "billetCinqDollars":
                return 5;
            case "billetDixDollars":
                return 10;
            case "billetVingtDollars":
                return 20;
            case "billetCinquanteDollars":
                return 50;
            case "billetCentDollars":
                return 100;
            default:
                throw new IllegalArgumentException("Type de billet inconnu : " + typeDollar);
        }
    }

    public double calculerMontantTotal() {
        return  billetUnDollar * 1 +
                billetDeuxDollars * 2 +
                billetCinqDollars * 5 +
                billetDixDollars * 10 +
                billetVingtDollars * 20 +
                billetCinquanteDollars * 50 +
                billetCentDollars * 100;
    }

    // Vous pouvez ajouter d'autres méthodes ou constructeurs si nécessaire


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBilletUnDollar() {
        return billetUnDollar;
    }

    public void setBilletUnDollar(int billetUnDollar) {
        this.billetUnDollar = billetUnDollar;
    }

    public int getBilletDeuxDollars() {
        return billetDeuxDollars;
    }

    public void setBilletDeuxDollars(int billetDeuxDollars) {
        this.billetDeuxDollars = billetDeuxDollars;
    }

    public int getBilletCinqDollars() {
        return billetCinqDollars;
    }

    public void setBilletCinqDollars(int billetCinqDollars) {
        this.billetCinqDollars = billetCinqDollars;
    }

    public int getBilletDixDollars() {
        return billetDixDollars;
    }

    public void setBilletDixDollars(int billetDixDollars) {
        this.billetDixDollars = billetDixDollars;
    }

    public int getBilletVingtDollars() {
        return billetVingtDollars;
    }

    public void setBilletVingtDollars(int billetVingtDollars) {
        this.billetVingtDollars = billetVingtDollars;
    }

    public int getBilletCinquanteDollars() {
        return billetCinquanteDollars;
    }

    public void setBilletCinquanteDollars(int billetCinquanteDollars) {
        this.billetCinquanteDollars = billetCinquanteDollars;
    }

    public int getBilletCentDollars() {
        return billetCentDollars;
    }

    public void setBilletCentDollars(int billetCentDollars) {
        this.billetCentDollars = billetCentDollars;
    }
}
