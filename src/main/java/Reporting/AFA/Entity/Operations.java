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
    private String agent;
    @Enumerated(EnumType.STRING)
    private NatureOperation natureOperation;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    @Enumerated(EnumType.STRING)
    private Service service;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private double montant;
    private String autres;
    private String statut;

    public Operations() {
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.date = now.format(formatter);
        this.id=generateId();




    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }


    public enum Service{
        Orange_money,Free_money,Wave,Wari,Wizall,Ecobank,Western_union,Ria,Moneygram,Smallworld,BnB,Askia,Sunu_Assurance,
        Creation_Entreprise,Cartes,Natte
    };




    // Constructeurs, getters et setters à ajouter

    public enum NatureOperation{
        Dépôt,retrait,Retrait_Code,Seddo,Paiement_de_Facture,Achat_Credit_Telephonique,IZI,
        Envoie,Encaissement_Assurance,Decaissement_Assurance,Encaissement_Création_Entreprise,Décaissement_Création_Entreprise,Encaissement_Carte,Decaissement_Carte};
    public enum Civilite {
        Mr,Mme
    };

    @Override
    public String toString() {
        return "Operations{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", agent='" + agent + '\'' +
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
