package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class OuvertureCompte {
    @Id
    private String id;

    private Date date;
    private String agent;

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

    @Enumerated(EnumType.STRING)
    private Pack pack;

    private double montantDepotInitial;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public OuvertureCompte() {
        this.id = generateId();
        this.date = new Date();
    }

    // Enumérations
    public enum TypeCompte {
        Compte_Courant, Compte_Epargne
    }

    public enum Civilite {
        Mr, Mme
    }

    public enum Pack {
        Ganalé, Kilifa, Wurus
    }
}

