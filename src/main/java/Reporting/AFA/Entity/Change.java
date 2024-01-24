package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Change {

    @Id
    private String id;

    private String date;
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

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Change() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
    }

    // Enumérations
    public enum Service {
        Achat_de_Devise("Achat De Devise");

        private final String label;

        Service(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Devise {
        EUR, XOF, USD
    }
}
