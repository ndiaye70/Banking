package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
//@NoArgsConstructor

public class Operations implements Identifiable {
    @Id
    private String id;

    private String date;
    @Enumerated(EnumType.STRING)
    private NatureOperation natureOperation;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    @Enumerated(EnumType.STRING)
    private Service service;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private Double montant;
    private String autres;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    public Operations() {
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
        this.id=generateId();




    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }


    public enum Service {
        Orange_money("Orange Money"),
        Free_money("Free Money"),
        Wave("Wave"),
        Wari("Wari"),
        Wizall("Wizall"),
        Ecobank("Ecobank"),
        Western_union("Western Union"),
        Ria("Ria"),
        Moneygram("Moneygram"),
        Smallworld("Smallworld"),
        BnB("BnB"),
        Askia("Askia"),
        Sunu_Assurance("Sunu Assurance"),
        Creation_Entreprise("Création Entreprise"),
        Cartes("Cartes"),
        Natte("Natte");

        private final String label;

        Service(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum NatureOperation {
        Dépôt("Dépôt"),
        retrait("Retrait"),
        RetraitCode("Retrait Code"),
        Seddo("Seddo"),
        Paiement_de_Facture("Paiement de Facture"),
        Achat_Credit_Telephonique("Achat Crédit Téléphonique"),
        IZI("IZI"),
        Envoie("Envoi"),
        Encaissement_Assurance("Encaissement Assurance"),
        Decaissement_Assurance("Décaissement Assurance"),
        Encaissement_Création_Entreprise("Encaissement Création Entreprise"),
        Décaissement_Création_Entreprise("Décaissement Création Entreprise"),
        Encaissement_Carte("Encaissement Carte"),
        Decaissement_Carte("Décaissement Carte");

        private final String label;

        NatureOperation(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }






    // Constructeurs, getters et setters à ajouter


    public enum Civilite {
        Mr("M."),Mme("Mme");


        private final String label;

        Civilite(String label){
            this.label=label;

        }
        public String getLabel() {
            return label;
        }

    }

    @Override
    public String toString() {
        return "Operations{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", natureOperation=" + natureOperation +
                ", civilite=" + civilite +
                ", service=" + service +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", montant=" + montant +
                ", autres='" + autres + '\'' +
                ", statut='" + statut + '\'' +
                '}';

    }





}
