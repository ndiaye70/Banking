package Reporting.AFA.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor

public class CourDuJour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double achatEuro;
    private double venteEuro;
    private double achatUSD;
    private double venteUSD;


    public CourDuJour() {
    }
}
