package Reporting.AFA.dto;

import Reporting.AFA.Entity.OperationsBancaires;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationsBancairesDto {
    private String banques;
    private String natureOperations;
    private String civilite;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private double montant;
    private double commissions;
    private String autres;
    private String statut;
    private String numeroCompte;

    public OperationsBancaires toEntity() {
        OperationsBancaires operationsBancaires = new OperationsBancaires();
        operationsBancaires.setBanques(OperationsBancaires.Banques.valueOf(banques));
        operationsBancaires.setNatureOperations(OperationsBancaires.NatureOperations.valueOf(natureOperations));
        operationsBancaires.setCivilite(OperationsBancaires.Civilite.valueOf(civilite));
        operationsBancaires.setNom(nom);
        operationsBancaires.setPrenom(prenom);
        operationsBancaires.setNumeroTelephone(numeroTelephone);
        operationsBancaires.setMontant(montant);
        operationsBancaires.setCommissions(commissions);
        operationsBancaires.setAutres(autres);
        operationsBancaires.setStatut(statut);
        operationsBancaires.setNumeroCompte(numeroCompte);
        return operationsBancaires;
    }
}

