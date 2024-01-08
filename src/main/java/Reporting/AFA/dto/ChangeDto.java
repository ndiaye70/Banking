package Reporting.AFA.dto;

import Reporting.AFA.Entity.Change;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDto {

    private String agent;
    private String prenom;
    private String nom;
    private String service;
    private String deviseRecu;
    private String deviseRemis;
    private double montantRecu;
    private double montantRemis;

    public Change toEntity() {
        Change change = new Change();
        change.setAgent(agent);
        change.setPrenom(prenom);
        change.setNom(nom);
        change.setService(Change.Service.valueOf(service));
        change.setDeviseRecu(Change.Devise.valueOf(deviseRecu));
        change.setDeviseRemis(Change.Devise.valueOf(deviseRemis));
        change.setMontantRecu(montantRecu);
        change.setMontantRemis(montantRemis);
        return change;
    }
}
