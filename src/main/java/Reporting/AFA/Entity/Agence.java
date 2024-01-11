package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agence_sequence")
    @SequenceGenerator(name = "agence_sequence", sequenceName = "agence_sequence", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String nomAgence;

    @Column(unique = true)
    private String code;

    public Agence() {


        // Constructeur par défaut nécessaire pour JPA
    }


}
