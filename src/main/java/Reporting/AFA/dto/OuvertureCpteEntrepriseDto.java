package Reporting.AFA.dto;

import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OuvertureCpteEntrepriseDto {
    private String agent;
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
        ouvertureCpteEntreprise.setAgent(agent);
        ouvertureCpteEntreprise.setFormeJuridique(OuvertureCpteEntreprise.FormeJuridique.valueOf(formeJuridique));
        ouvertureCpteEntreprise.setRccm(rccm);
        ouvertureCpteEntreprise.setNinea(ninea);
        ouvertureCpteEntreprise.setDenominationSocial(denominationSocial);
        ouvertureCpteEntreprise.setMontantDepotInitial(montantDepotInitial);
        ouvertureCpteEntreprise.setNom(nom);
        ouvertureCpteEntreprise.setPrenom(prenom);
        ouvertureCpteEntreprise.setFonctions(fonctions);
        ouvertureCpteEntreprise.setNumCNI_Passeport(numCNI_Passeport);
        ouvertureCpteEntreprise.setNumTelephone(numTelephone);
        ouvertureCpteEntreprise.setAdresse(adresse);

        return ouvertureCpteEntreprise;
    }
}
