package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class OuvertureCompte {
    @Id
    private String id;

    private String date;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;

    @Enumerated(EnumType.STRING)
    private Civilite civilite;

    private String nom;
    private String prenom;
    private String numTel;
    private String adresse;
    private String activite;
    private String numCni_Passeport;
    private String nomConjoint_Conjointe;
    private String nom_PrenomMere;
    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;


    @Enumerated(EnumType.STRING)
    private Pack pack;

    private Double montantDepotInitial;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public OuvertureCompte() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
    }

    // Enumérations
    public enum TypeCompte {
        Compte_Courant("Compte Courant"), Compte_Epargne("Compte Epargne");

        private final String label;

        TypeCompte(String label) {
            this.label = label;

        }

        public String getLabel() {
            return label;
        }
        }


    public enum Civilite {
        Mr, Mme
    }

    public enum Pack {
        Ganalé, Kilifa, Wurus
    }
}

