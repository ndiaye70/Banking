package Reporting.AFA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

// Dans votre package Reporting.AFA.dto
public class CustomOperationResult {
    private String id;
    private String caissier;
    private String service;
    private String natureOperation;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private Double montant;
    private String autres;
    private String statut;

    // Constructeur, getters et setters
}
