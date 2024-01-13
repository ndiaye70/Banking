package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Data
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    // Utilisation de la relation entre Agent et Agence pour obtenir l'agence correspondante
    @Transient
    private Agence agence;

    private String natureCaisse;

    @OneToOne
    private Euro euros;

    @Getter
    private double montantTotal;

    public Caisse() {
        this.id = 0L;
        this.dateCreation = new Date();
        this.euros = new Euro();
        this.montantTotal = 0.0;
    }

    public Caisse(Euro euros, Agent agent,String natureCaisse) {
        this.id = 0L;
        this.dateCreation = new Date();
        this.euros = euros;
        this.agent = agent;
        this.agence = agent.getAgence();
        if (this.euros != null) {
            this.montantTotal = euros.calculerMontantTotal();
        } else {
            this.montantTotal = 0.0;
        }
    }

    public void calculerMontantTotal() {
        montantTotal = euros.calculerMontantTotal();

    }
}
