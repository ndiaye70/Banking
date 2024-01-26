package Reporting.AFA.dto;

import Reporting.AFA.Entity.Approvisionnement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovisionnementDto {
    private double montant;
    private String type;
    private String origine;
    private String compteCaisse1;
    private String destination;
    private String compteCaisse2;
    private String devise;
    private String statut;

    public Approvisionnement toEntity() {
        Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setMontant(montant);
        approvisionnement.setType(Approvisionnement.TYPE.valueOf(type));
        approvisionnement.setOrigine(Approvisionnement.Origine.valueOf(origine));
        approvisionnement.setCompteCaisse1(Approvisionnement.Compte.valueOf(compteCaisse1));
        approvisionnement.setDestination(destination);
        approvisionnement.setCompteCaisse2(Approvisionnement.Compte.valueOf(compteCaisse2));
        approvisionnement.setDevise(Approvisionnement.Devise.valueOf(devise));
        approvisionnement.setStatut(statut);
        return approvisionnement;
    }
}
