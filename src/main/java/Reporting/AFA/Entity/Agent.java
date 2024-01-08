package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id")
    private Agence agence;

    @OneToOne
    @JoinColumn(name = "id")
    private AppUser user;

    // Constructeurs, getters et setters

    public Agent() {
        // Constructeur par défaut
    }

    public Agent(String nom, String prenom, Agence agence, AppUser user) {
        this.agence = agence;
        this.user = user;
    }

    // Autres méthodes, getters et setters
}
