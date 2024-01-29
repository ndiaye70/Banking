package Reporting.AFA.dto;

import Reporting.AFA.Entity.CourDuJour;
import lombok.Data;

@Data
public class CourDuJourDto {
    private double achatEuro;
    private double venteEuro;
    private double achatUSD;
    private double venteUSD;

    public CourDuJour toEntity() {
        CourDuJour courDuJour = new CourDuJour();
        courDuJour.setAchatEuro(this.achatEuro);
        courDuJour.setVenteEuro(this.venteEuro);
        courDuJour.setAchatUSD(this.achatUSD);
        courDuJour.setVenteUSD(this.venteUSD);
        return courDuJour;
    }
}
