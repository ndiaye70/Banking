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

    @Column(unique = true, nullable = false)
    private String code;

    public Agence() {
        // Constructeur par défaut nécessaire pour JPA
    }

    public Agence(String nomAgence) {
        this.nomAgence = nomAgence;
        this.code = generateCode();
    }

    @PrePersist
    private void beforePersist() {
        this.code = generateCode();
    }

    private String generateCode() {
        // Utilisation de l'id généré par la séquence pour garantir l'unicité des codes
        return String.format("AG-%03d", id);
    }
}
