package Reporting.AFA.dto;

import Reporting.AFA.Entity.SoldeCaisse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoldeCaisseDto {
    private double ouvertureCaisseXof;
    private double fermetureCaisseXof;
    private double ouvertureCaisseEur;
    private double fermetureCaisseEur;
    private double ouvertureCaisseUsd;
    private double fermetureCaisseUsd;

    // Constructeur vide
    public SoldeCaisseDto() {
    }

    // Méthode pour convertir le DTO en entité SoldeCaisse
    public SoldeCaisse toEntity() {
        SoldeCaisse soldeCaisse = new SoldeCaisse();
        soldeCaisse.setOuvertureCaisseXof(this.ouvertureCaisseXof);
        soldeCaisse.setFermetureCaisseXof(this.fermetureCaisseXof);
        soldeCaisse.setOuvertureCaisseEur(this.ouvertureCaisseEur);
        soldeCaisse.setFermetureCaisseEur(this.fermetureCaisseEur);
        soldeCaisse.setOuvertureCaisseUsd(this.ouvertureCaisseUsd);
        soldeCaisse.setFermetureCaisseUsd(this.fermetureCaisseUsd);
        return soldeCaisse;
    }
}
