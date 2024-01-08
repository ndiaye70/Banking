package Reporting.AFA.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EuroDto {
    private int billetCinqCents;
    private int billetDeuxCents;

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

    private int billetCent;
    private int billetCinquante;
    private int billetCinq;
    private int billetDix;
    private int billetVingt;

    // Ajoutez des méthodes d'accès et d'autres méthodes si nécessaire

    // Méthode de transformation de DTO en entité Euro

}
