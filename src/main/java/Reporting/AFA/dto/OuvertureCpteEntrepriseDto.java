package Reporting.AFA.dto;

import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OuvertureCpteEntrepriseDto {
    private String formeJuridique;
    private String rccm;
    private String ninea;
    private String denominationSocial;
    private String pack;
    private double montantDepotInitial;
    private String nom;
    private String prenom;
    private String fonctions;
    private String numCNI_Passeport;
    private String numTelephone;
    private String adresse;


    public OuvertureCpteEntreprise convertDtoToEntity() {

        OuvertureCpteEntreprise ouvertureCpteEntreprise = new OuvertureCpteEntreprise();

        // Appliquez les propriétés spécifiques à OuvertureCpteEntreprise
        OperationsDto ouvertureCpteEntrepriseDto;
        ouvertureCpteEntreprise.setFormeJuridique(OuvertureCpteEntreprise.FormeJuridique.valueOf(this.formeJuridique));
        ouvertureCpteEntreprise.setRccm(this.rccm);
        ouvertureCpteEntreprise.setNinea(this.ninea);
        ouvertureCpteEntreprise.setDenominationSocial(this.denominationSocial);
        ouvertureCpteEntreprise.setMontantDepotInitial(this.montantDepotInitial);
        ouvertureCpteEntreprise.setNom(this.nom);
        ouvertureCpteEntreprise.setPrenom(this.prenom);
        ouvertureCpteEntreprise.setFonctions(this.fonctions);
        ouvertureCpteEntreprise.setNumCNI_Passeport(this.numCNI_Passeport);
        ouvertureCpteEntreprise.setNumTel(this.numTelephone);
        ouvertureCpteEntreprise.setAdresse(this.adresse);

        return ouvertureCpteEntreprise;
    }
}
