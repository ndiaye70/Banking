package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class XOF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int billetDixMille;
    private int billetCinqMille;
    private int billetDeuxMille;
    private int billetMille;
    private int billetCinqCent;
    private int pieceCinqCent;
    private int pieceDeuxCent;
    private int pieceCent;
    private int pieceCinquante;
    private int pieceVingtCinq;
    private int pieceDix;
    private int pieceCinq;

    // Ajoutez d'autres attributs si nécessaire

    // Getters et setters



    public double calculerMontantTotal() {
        return  billetDixMille * 10000 +
                billetCinqMille * 5000 +
                billetDeuxMille * 2000 +
                billetMille * 1000 +
                billetCinqCent * 500 +
                pieceCinqCent *500 +
                pieceDeuxCent * 200 +
                pieceCent * 100 +
                pieceCinquante * 50 +
                pieceVingtCinq * 25 +
                pieceDix * 10 +
                pieceCinq * 5;
    }

    public XOF(){

    }

    // Ajoutez le switchCase si nécessaire pour les conversions
}
