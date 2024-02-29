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
    private Double montant;
    private Integer nineaCopie; // Nouvel attribut pour le nombre de copies de NINEA
    private Integer rcCopie; // Nouvel attribut pour le nombre de copies de RC
    private Integer passeportCopie; // Nouvel attribut pour le nombre de copies de Passeport/CNI
    private Integer photoCopie; // Nouvel attribut pour le nombre de copies de Photo d'identité
    private Integer ccCopie; // Nouvel attribut pour le nombre de copies d'Ancienne C.C
    private Integer cieCopie; // Nouvel attribut pour le nombre de copies d'Ancienne C.I.E

    public Carte toEntity() {
        Carte carte = new Carte();
        carte.setNomComplet(this.nomComplet);
        carte.setTel(this.Tel);
        carte.setService(Carte.Service.valueOf(this.service));
        carte.setMontant(this.montant);
        carte.setNineaCopie(this.nineaCopie); // Mettre à jour les copies de NINEA
        carte.setRcCopie(this.rcCopie); // Mettre à jour les copies de RC
        carte.setPasseportCopie(this.passeportCopie); // Mettre à jour les copies de Passeport/CNI
        carte.setPhotoCopie(this.photoCopie); // Mettre à jour les copies de Photo d'identité
        carte.setCcCopie(this.ccCopie); // Mettre à jour les copies d'Ancienne C.C
        carte.setCieCopie(this.cieCopie); // Mettre à jour les copies d'Ancienne C.I.E

        return carte;
    }

    public Carte toEntity1() {
        Carte carte = new Carte();
        carte.setNomComplet(this.nomComplet);
        carte.setTel(this.Tel);
        carte.setService(Carte.Service.valueOf(this.service));
        carte.setNineaCopie(this.nineaCopie); // Mettre à jour les copies de NINEA
        carte.setRcCopie(this.rcCopie); // Mettre à jour les copies de RC
        carte.setPasseportCopie(this.passeportCopie); // Mettre à jour les copies de Passeport/CNI
        carte.setPhotoCopie(this.photoCopie); // Mettre à jour les copies de Photo d'identité
        carte.setCcCopie(this.ccCopie); // Mettre à jour les copies d'Ancienne C.C
        carte.setCieCopie(this.cieCopie); // Mettre à jour les copies d'Ancienne C.I.E

        return carte;
    }
}
