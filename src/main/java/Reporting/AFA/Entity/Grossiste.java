package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.Data;


import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="Grossiste_BnB")
@Data
public class Grossiste {

    @Id
    private String id;

    private String date;


    private String NatureOperations;

    @Enumerated(EnumType.STRING)
    private Civilite civilite;

    private String nom;
    private String prenom;
    private String numTel;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private MontantPayePar montantPayePar;

    private Double frais;

    @Enumerated(EnumType.STRING)
    private FraisPayePar fraisPayePar;

    private String autres;
    private String statut;
    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;


    // Constructeur sans paramètres
    public Grossiste() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
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

    public enum MontantPayePar {
        Especes, Wave, OM
    }

    public enum FraisPayePar {
        Especes, Wave, OM
        // Ajoutez les valeurs spécifiques ici
    }
}
