package Reporting.AFA.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import Reporting.AFA.Entity.Carte;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteDto {
    private String nomComplet;
    private String Tel;
    private String service;
    private Double montant;// Enum pour le service, pas une chaîne

    public Carte toEntity() {
        Carte carte = new Carte();
        carte.setNomComplet(this.nomComplet);
        carte.setTel(this.Tel);
        carte.setService(Carte.Service.valueOf(this.service));
        carte.setMontant(this.montant);

        // Ajoutez d'autres conversions si nécessaire

        return carte;
    }

    public Carte toEntity1() {
        Carte carte = new Carte();
        carte.setNomComplet(this.nomComplet);
        carte.setTel(this.Tel);
        carte.setService(Carte.Service.valueOf(this.service));

        // Ajoutez d'autres conversions si nécessaire

        return carte;
    }
}

