package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class Dollars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public int billetUnDollar;
    public int billetDeuxDollars;
    public int billetCinqDollars;
    public int billetDixDollars;
    public int billetVingtDollars;
    public int billetCinquanteDollars;
    public int billetCentDollars;

    public Dollars() {
        // Le constructeur privé évite l'instanciation de cette classe.
    }



    public double calculerMontantTotal() {
        return billetUnDollar * 1 +
                billetDeuxDollars * 2 +
                billetCinqDollars * 5 +
                billetDixDollars * 10 +
                billetVingtDollars * 20 +
                billetCinquanteDollars * 50 +
                billetCentDollars * 100;
    }
}

    // Vous pouvez ajouter d'autres méthodes ou constructeurs si nécessaire


