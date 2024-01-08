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
public class OperationsBancaires {
    @Id
    private String id;

    private Date date;
    private String agent;

    @Enumerated(EnumType.STRING)
    private Banques banques;

    @Enumerated(EnumType.STRING)
    private NatureOperations natureOperations;

    private String numeroCompte;

    @Enumerated(EnumType.STRING)
    private Civilite civilite;

    private String nom;
    private String prenom;
    private String numeroTelephone;
    private double montant;
    private double commissions;
    private String autres;
    private String statut;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public OperationsBancaires() {
        this.id = generateId();
        this.date = new Date();
    }

    // Enumérations
    public enum Banques {
        BNDE, UBA
    }

    public enum NatureOperations {
        Depot, Retrait, Virement, Paiement
    }

    public enum Civilite {
        Mr, Mme
    }
}

