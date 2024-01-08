package Reporting.AFA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import Reporting.AFA.Entity.Grossiste;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrossisteDto {

    private String civilite;
    private String nom;
    private String prenom;
    private String numTel;
    private double montant;
    private String payePar;
    private double frais;
    private String payesPar;
    private String autres;
    private String statut;

    public Grossiste toEntity() {
        Grossiste grossiste = new Grossiste();
        // Conversion des chaînes en énumérations
        grossiste.setCivilite(Grossiste.Civilite.valueOf(civilite));
        grossiste.setNom(nom);
        grossiste.setPrenom(prenom);
        grossiste.setNumTel(numTel);
        grossiste.setMontant(montant);
        grossiste.setPayePar(Grossiste.PayePar.valueOf(payePar));
        grossiste.setFrais(frais);
        grossiste.setPayesPar(Grossiste.PayesPar.valueOf(payesPar));
        grossiste.setAutres(autres);
        grossiste.setStatut(statut);
        return grossiste;
    }
}