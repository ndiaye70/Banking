package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class CaisseDollars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private Date dateCreation;


    @Enumerated(EnumType.STRING)
    private Caisse.NatureCaisse natureCaisse;

    @Transient
    private Agence agence;

    @OneToOne
    private Dollars dollars;

    private double montantTotal;

    public CaisseDollars() {
        this.id = 0L;
        this.dateCreation = new Date();
        this.dollars = new Dollars();
        this.montantTotal = 0.0;
    }

    public CaisseDollars(Dollars dollars,Agent agent,String natureCaisse) {
        this.id = 0L;
        this.dateCreation = new Date();
        this.dollars = dollars;
        this.agent = agent;
        this.agence = agent.getAgence();
        if (this.dollars != null) {
            this.montantTotal = dollars.calculerMontantTotal();
        } else {
            this.montantTotal = 0.0;
        }
    }

    public void calculerMontantTotal() {
        montantTotal = dollars.calculerMontantTotal();
    }

    public enum NatureCaisse {
        Ouverture_Caisse("Ouverture Caisse"),Billetage("Billetage"),Fermeture_Caisse("Fermeture Caisse");


        private final String label;

        NatureCaisse(String label){
            this.label=label;

        }
        public String getLabel() {
            return label;
        }

    }




}

