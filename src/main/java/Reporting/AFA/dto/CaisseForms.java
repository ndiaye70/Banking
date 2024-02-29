package Reporting.AFA.dto;

import Reporting.AFA.Entity.Dollars;
import Reporting.AFA.Entity.Euro;
import Reporting.AFA.Entity.XOF;
import lombok.Data;

@Data

public class CaisseForms {
    private Euro euroDto;
    private Dollars dollarsDto;
    private XOF xofDto;

    // Ajoutez les getters et setters nécessaires
}
