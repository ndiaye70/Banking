package Reporting.AFA.Entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Change {

    @Id
    private String id;

    private Date date;
    private String agent;
    private String prenom;
    private String nom;

    @Enumerated(EnumType.STRING)
    private Service service;

    @Enumerated(EnumType.STRING)
    private Devise deviseRecu;

    @Enumerated(EnumType.STRING)
    private Devise deviseRemis;

    private double montantRecu;
    private double montantRemis;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Change() {
        this.id = generateId();
        this.date = new Date();
    }

    // Enumérations
    public enum Service {
        Achat_de_Devise
    }

    public enum Devise {
        EUR, XOF, USD
    }
}
