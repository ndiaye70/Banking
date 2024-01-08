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
public class Approvisionnement {
    @Id
    private String id;

    private Date date;
    private String agent;
    private double montant;
    private String type;
    private String origine;
    private String compteCaisse1;
    private String destination;
    private String compteCaisse2;

    @Enumerated(EnumType.STRING)
    private Devise devise;

    private String statut;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Approvisionnement() {
        this.id = generateId();
        this.date = new Date();
    }

    // Enumération
    public enum Devise {
        XOF, EUR, USD
    }
}

