package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Data
public class CaisseXOF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private String natureCaisse;

    @Transient
    private Agence agence;



    @OneToOne
    private XOF xof;
    private Date dateCreation;
    @Getter
    private double montantTotal;

    // Ajoutez d'autres attributs si nécessaire

    // Getters et setters

    public CaisseXOF() {
        this.id = 0L;
        this.xof = new XOF();
        this.dateCreation = new Date();
        this.montantTotal = 0.0;
    }

    public CaisseXOF(XOF xof,Agent agent,String natureCaisse) {
        this.id = 0L;
        this.dateCreation = new Date();
        this.xof = xof;
        this.agent = agent;
        this.agence = agent.getAgence();
        if (this.xof != null) {
            this.montantTotal = xof.calculerMontantTotal();
        } else {
            this.montantTotal = 0.0;
        }
    }

    public void calculerMontantTotal() {
        this.montantTotal = xof.calculerMontantTotal();
    }


}
