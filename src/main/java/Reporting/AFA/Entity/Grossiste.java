package Reporting.AFA.Entity;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;


import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="Grossiste_BnB")
@Data
public class Grossiste {

    @Id
    private String id;

    private Date date;


    private String NatureOperations;

    @Enumerated(EnumType.STRING)
    private Civilite civilite;

    private String nom;
    private String prenom;
    private String numTel;
    private double montant;

    @Enumerated(EnumType.STRING)
    private PayePar payePar;

    private double frais;

    @Enumerated(EnumType.STRING)
    private PayesPar payesPar;

    private String autres;
    private String statut;

    // Constructeur sans paramètres
    public Grossiste() {
        this.id = generateId();
        this.date = new Date();
        this.NatureOperations = "Depot_grossiste_BNB";
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    // Getters and setters

    // Enumérations
    public enum Civilite {
        M, Mme
    }

    public enum PayePar {
        Especes, Wave, OM
    }

    public enum PayesPar {
        Especes, Wave, OM
        // Ajoutez les valeurs spécifiques ici
    }
}
