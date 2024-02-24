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


    @Enumerated(EnumType.STRING)
    private NatureCaisse natureCaisse;

    @OneToOne
    private Euro euros;

    @OneToOne
    private Dollars dollars;

    @OneToOne
    private XOF xof;

    private double montantDollars;
    private  double montantEuros;
    private  double montantFcfa;

    public Caisse() {
        this.id = 0L;
        this.dateCreation = new Date();
        this.euros = new Euro();
    }

    public Caisse(Euro euros, Agent agent,String natureCaisse) {
        this.id = 0L;
        this.dateCreation = new Date();
        this.euros = euros;
        this.agent = agent;
        this.agence = agent.getAgence();

    }

    public void calculerMontantDollars() {
        montantDollars = dollars.calculerMontantTotal();

    }

    public void calculerMontantEuros() {
        montantEuros = euros.calculerMontantTotal();

    }
    public void calculerMontantFcfa() {
        montantFcfa =xof.calculerMontantTotal();

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
