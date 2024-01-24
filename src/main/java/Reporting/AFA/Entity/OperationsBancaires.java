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
public class OperationsBancaires {
    @Id
    private String id;

    private String date;

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
    private Double montant;
    private Double commissions;
    private String autres;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public OperationsBancaires() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
    }

    // Enumérations
    public enum Banques {
        BNDE, UBA
    }

    public enum NatureOperations {
        Depot("Dépôts"), Retrait("Retrait"),Achat_Carte_Prépayée("Achat Carte Prépayée") ,
        Recharge_Carte_Prépayée("Recharge Carte Prépayée")  ,Décaissement_UBA_CP("Décaissement UBA (CP)"),
        Dépôt_initial("Dépôt initial") ;

        private final String label;

        NatureOperations(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;

        }
        }

    public enum Civilite {
        Mr, Mme
    }
}

