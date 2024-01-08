package Reporting.AFA.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DollarsDto {
    private int billetUnDollar;
    private int billetDeuxDollars;
    private int billetCinqDollars;
    private int billetDixDollars;
    private int billetVingtDollars;
    private int billetCinquanteDollars;
    private int billetCentDollars;

    public int getBilletUnDollar() {
        return billetUnDollar;
    }

    public int getBilletDeuxDollars() {
        return billetDeuxDollars;
    }

    public int getBilletCinqDollars() {
        return billetCinqDollars;
    }

    public int getBilletDixDollars() {
        return billetDixDollars;
    }

    public int getBilletVingtDollars() {
        return billetVingtDollars;
    }

    public int getBilletCinquanteDollars() {
        return billetCinquanteDollars;
    }

    public int getBilletCentDollars() {
        return billetCentDollars;
    }
// Ajoutez des méthodes d'accès et d'autres méthodes si nécessaire

    // Méthode de transformation de DTO en entité Dollars
}
