package Reporting.AFA.dto;

import lombok.Data;

@Data

// Dans votre package Reporting.AFA.dto
public class CustomGrossisteResult {
    private String id;
    private String date;
    private String caissier;
    private String nom;
    private String prenom;
    private String numTel;
    private Double montant;
    private Double frais;
    private String autres;
    private String statut;
    private MontantPayePar montantPayépar;
    private FraisPayepar fraisPayépar;


    public enum MontantPayePar {
        Especes, Wave, OM
    }

    public enum FraisPayepar {
        Especes, Wave, OM
        // Ajoutez les valeurs spécifiques ici
    }

    // Constructeur, getters et setters
}
