package Reporting.AFA.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class XOFDto {
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

    public int getBilletDixMille() {
        return billetDixMille;
    }

    public int getBilletCinqMille() {
        return billetCinqMille;
    }

    public int getBilletDeuxMille() {
        return billetDeuxMille;
    }

    public int getBilletMille() {
        return billetMille;
    }

    public int getBilletCinqCent() {
        return billetCinqCent;
    }

    public int getPieceCinqCent() {
        return pieceCinqCent;
    }

    public int getPieceDeuxCent() {
        return pieceDeuxCent;
    }

    public int getPieceCent() {
        return pieceCent;
    }

    public int getPieceCinquante() {
        return pieceCinquante;
    }

    public int getPieceVingtCinq() {
        return pieceVingtCinq;
    }

    public int getPieceDix() {
        return pieceDix;
    }

    public int getPieceCinq() {
        return pieceCinq;
    }
    // Ajoutez d'autres attributs si nécessaire
}
