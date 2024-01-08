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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBilletDixMille() {
        return billetDixMille;
    }

    public void setBilletDixMille(int billetDixMille) {
        this.billetDixMille = billetDixMille;
    }

    public int getBilletCinqMille() {
        return billetCinqMille;
    }

    public void setBilletCinqMille(int billetCinqMille) {
        this.billetCinqMille = billetCinqMille;
    }

    public int getBilletDeuxMille() {
        return billetDeuxMille;
    }

    public void setBilletDeuxMille(int billetDeuxMille) {
        this.billetDeuxMille = billetDeuxMille;
    }

    public int getBilletMille() {
        return billetMille;
    }

    public void setBilletMille(int billetMille) {
        this.billetMille = billetMille;
    }

    public int getBilletCinqCent() {
        return billetCinqCent;
    }

    public void setBilletCinqCent(int billetCinqCent) {
        this.billetCinqCent = billetCinqCent;
    }

    public int getPieceCinqCent() {
        return pieceCinqCent;
    }

    public void setPieceCinqCent(int pieceCinqCent) {
        this.pieceCinqCent = pieceCinqCent;
    }

    public int getPieceDeuxCent() {
        return pieceDeuxCent;
    }

    public void setPieceDeuxCent(int pieceDeuxCent) {
        this.pieceDeuxCent = pieceDeuxCent;
    }

    public int getPieceCent() {
        return pieceCent;
    }

    public void setPieceCent(int pieceCent) {
        this.pieceCent = pieceCent;
    }

    public int getPieceCinquante() {
        return pieceCinquante;
    }

    public void setPieceCinquante(int pieceCinquante) {
        this.pieceCinquante = pieceCinquante;
    }

    public int getPieceVingtCinq() {
        return pieceVingtCinq;
    }

    public void setPieceVingtCinq(int pieceVingtCinq) {
        this.pieceVingtCinq = pieceVingtCinq;
    }

    public int getPieceDix() {
        return pieceDix;
    }

    public void setPieceDix(int pieceDix) {
        this.pieceDix = pieceDix;
    }

    public int getPieceCinq() {
        return pieceCinq;
    }

    public void setPieceCinq(int pieceCinq) {
        this.pieceCinq = pieceCinq;
    }

    public double calculerMontantTotal() {
        return billetDixMille * 10000 +
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

    // Ajoutez le switchCase si nécessaire pour les conversions
}
