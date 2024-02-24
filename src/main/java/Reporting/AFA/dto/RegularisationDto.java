package Reporting.AFA.dto;

import Reporting.AFA.Entity.Regularisation;
import lombok.Data;

@Data
public class RegularisationDto {
    private String date;
    private String libelle;
    private double montant;
    private String compteCaisse;
    private String regularisateur;

    public Regularisation toEntity() {
        Regularisation regularisation = new Regularisation();
        regularisation.setLibelle(this.libelle);
        regularisation.setMontant(this.montant);
        regularisation.setCompteCaisse(Regularisation.CompteCaisse.valueOf(this.compteCaisse));
        regularisation.setRegularisateur(Regularisation.Regularisateur.valueOf(this.regularisateur));
        return regularisation;
    }
}
