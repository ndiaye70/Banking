package Reporting.AFA.Entity;

import lombok.Data;

@Data

public class RecapMontant {
    private String service;
    private Double montant;

    public RecapMontant(String service, Double montant) {
        this.service = service;
        this.montant = montant;
    }
}
