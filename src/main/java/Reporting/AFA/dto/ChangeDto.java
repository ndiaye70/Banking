package Reporting.AFA.dto;

import Reporting.AFA.Entity.Change;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDto {

    private String prenom;
    private String nom;
    private String service;
    private String deviseRecu;
    private String deviseRemis;
    private double montantRecu;

    public Change toEntity() {
        Change change = new Change();
        change.setPrenom(this.prenom);
        change.setNom(this.nom);
        change.setService(Change.Service.valueOf(this.service));
        change.setDeviseRecu(Change.Devise.valueOf(this.deviseRecu));
        change.setDeviseRemis(Change.Devise.valueOf(this.deviseRemis));
        change.setMontantRecu(this.montantRecu);
        return change;
    }
}
