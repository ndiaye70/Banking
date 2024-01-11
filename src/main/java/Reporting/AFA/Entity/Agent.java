package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_agence")
    private Agence agence;

    @OneToOne
    @JoinColumn(name = "id_User")
    private AppUser user;





    // Constructeurs, getters et setters




    // Autres méthodes, getters et setters
}
