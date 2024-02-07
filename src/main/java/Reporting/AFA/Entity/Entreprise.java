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
public class Entreprise {
    @Id
    private String id;
    private String dateCreation;
    private String nom;
    private String prenom;
    private String numTel;
    private String cni;
    private String nom_Entreprise;

    @Enumerated(EnumType.STRING)
    private TypeEntreprise typeEntreprise;

    @Enumerated(EnumType.STRING)
    private Demande demande;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;


    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Entreprise() {




        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dateCreation = now.format(formatter);

    }

    public enum TypeEntreprise {
        GIE("GIE"), Entreprise_Individuelle_avec_Nom_Commercial("Entreprise Individuelle avec Nom Commercial"), Entreprise_Individuelle_sans_Nom_Commercial("Entreprise Individuelle sans Nom Commercial");
        private final String label;

        TypeEntreprise(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Demande {
        CREATION_ENTREPRISE_INDIVIDUELLE_SANS_NOM_COMMERCIAL("Création d'entreprise individuelle sans nom commercial", 26900),
        CREATION_ENTREPRISE_INDIVIDUELLE_AVEC_NOM_COMMERCIAL("Création d'entreprise individuelle avec nom commercial", 36900),
        CREATION_GIE("Création d'un GIE", 99500),
        CHANGEMENT_NOM_COMMERCIAL("Changement de nom commercial", 32500),
        DEMANDE_NOM_COMMERCIAL_RC_SNC("Demande de nom commercial sur RC SNC", 42000),
        CHANGEMENT_SUPPRESSION_AJOUT_ACTIVITES("Changement/suppression/ Ajout d'activités", 20000),
        CHANGEMENT_SIEGE_SOCIAL("Changement de siège social", 20000),
        CHANGEMENT_ADRESSE("Changement d'adresse", 20000),
        CHANGEMENT_NUMERO_TEL("Changement de numéro de Tél", 20000),
        CHANGEMENT_DIRIGEANT("Changement de dirigeant", 20000),
        CHANGEMENT_SITUATION_MATRIMONIAL("Changement de situation matrimonial", 20000),
        CHANGEMENT_NUM_CNI_PASSEPORT("Changement de N° CNI/ Passeport", 20000),
        DEMANDE_DUPLICATA_RC_NINEA("Demande de duplicata RC ET NINEA", 20000),
        DEMANDE_RADIATION("Demande de radiation", 20000),
        DEMANDE_NINEA("Demande de Ninea", 10000);

        private final String label;
        private final double montant;

        Demande(String label, double montant) {
            this.label = label;
            this.montant = montant;
        }

        public String getLabel() {
            return label;
        }

        public double getMontant() {
            return montant;
        }
    }
}

