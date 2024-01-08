package Reporting.AFA.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter

public class CaisseDto {
    private int billetCinqCents;
    private int billetDeuxCents;
    private int billetCent;
    private int billetCinquante;
    private int billetCinq;
    private int billetDix;
    private int billetVingt;

    public int getBilletCinqCents() {
        return billetCinqCents;
    }

    public int getBilletDeuxCents() {
        return billetDeuxCents;
    }

    public int getBilletCent() {
        return billetCent;
    }

    public int getBilletCinquante() {
        return billetCinquante;
    }

    public int getBilletCinq() {
        return billetCinq;
    }

    public int getBilletDix() {
        return billetDix;
    }

    public int getBilletVingt() {
        return billetVingt;
    }

    public void setBilletCinqCents(int billetCinqCents) {
        this.billetCinqCents = billetCinqCents;
    }

    public void setBilletDeuxCents(int billetDeuxCents) {
        this.billetDeuxCents = billetDeuxCents;
    }

    public void setBilletCent(int billetCent) {
        this.billetCent = billetCent;
    }

    public void setBilletCinquante(int billetCinquante) {
        this.billetCinquante = billetCinquante;
    }

    public void setBilletCinq(int billetCinq) {
        this.billetCinq = billetCinq;
    }

    public void setBilletDix(int billetDix) {
        this.billetDix = billetDix;
    }

    public void setBilletVingt(int billetVingt) {
        this.billetVingt = billetVingt;
    }
}
