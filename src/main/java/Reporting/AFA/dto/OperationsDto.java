package Reporting.AFA.dto;

import Reporting.AFA.Entity.Operations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OperationsDto {


    public String getAgent() {
        return agent;
    }

    public String getService() {
        return service;
    }

    public String getNatureOperation() {
        return natureOperation;
    }

    public String getCivilite() {
        return civilite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public double getMontant() {
        return montant;
    }

    public String getAutres() {
        return autres;
    }

    public String getStatut() {
        return statut;
    }
    private String agent;

    private String service;
    private String natureOperation;
    private String civilite;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private double montant;
    private String autres;
    private String statut;


    public Operations convertDtoToEntity() {
        Operations operations = new Operations();
        operations.setAgent(agent);
        operations.setService(Operations.Service.valueOf(service));
        operations.setNatureOperation(Operations.NatureOperation.valueOf(natureOperation));
        operations.setCivilite(Operations.Civilite.valueOf(civilite));
        operations.setNom(nom);
        operations.setPrenom(prenom);
        operations.setNumeroTelephone(numeroTelephone);
        operations.setMontant(montant);
        operations.setAutres(autres);
        operations.setStatut(statut);
        return operations;
    }
}
