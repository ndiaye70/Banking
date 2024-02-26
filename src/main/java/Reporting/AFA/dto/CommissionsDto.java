package Reporting.AFA.dto;

import Reporting.AFA.Entity.Commissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionsDto {

    private double commissionsOM;
    private double commissionsFreeMoney;
    private double commissionsWave;
    private double commissionsWizall;
    private double commissionsWesternUnion;
    private double commissionsRia;
    private double commissionsMoneygram;
    private double commissionsEcobank;
    private double commissionsSmallWorld;
    private double commissionsBnB;
    private double commissionsAskia;
    private double commissionsSunuAssurance;
    private double commissionsCreationEntreprise;
    private double commissionsCartesComImpExp;
    private double commissionsChange;
    private double commissionsGrossisteBnb;
    private double commissionsBndeDepotRetrait;
    private double commissionsOuvertureCompteBnde;
    private double commissionsUbaCartesPrepayees;
    private double commissionsUbaDepotRetrait;

    public Commissions toEntity() {
        Commissions commissionsEntity = new Commissions();

        commissionsEntity.setCommissionsOM(this.commissionsOM);
        commissionsEntity.setCommissionsFreeMoney(this.commissionsFreeMoney);
        commissionsEntity.setCommissionsWave(this.commissionsWave);
        commissionsEntity.setCommissionsWizall(this.commissionsWizall);
        commissionsEntity.setCommissionsWesternUnion(this.commissionsWesternUnion);
        commissionsEntity.setCommissionsRia(this.commissionsRia);
        commissionsEntity.setCommissionsMoneygram(this.commissionsMoneygram);
        commissionsEntity.setCommissionsEcobank(this.commissionsEcobank);
        commissionsEntity.setCommissionsSmallWorld(this.commissionsSmallWorld);
        commissionsEntity.setCommissionsBnB(this.commissionsBnB);
        commissionsEntity.setCommissionsAskia(this.commissionsAskia);
        commissionsEntity.setCommissionsSunuAssurance(this.commissionsSunuAssurance);
        commissionsEntity.setCommissionsCreationEntreprise(this.commissionsCreationEntreprise);
        commissionsEntity.setCommissionsCartesComImpExp(this.commissionsCartesComImpExp);
        commissionsEntity.setCommissionsChange(this.commissionsChange);
        commissionsEntity.setCommissionsGrossisteBnb(this.commissionsGrossisteBnb);
        commissionsEntity.setCommissionsBndeDepotRetrait(this.commissionsBndeDepotRetrait);
        commissionsEntity.setCommissionsOuvertureCompteBnde(this.commissionsOuvertureCompteBnde);
        commissionsEntity.setCommissionsUbaCartesPrepayees(this.commissionsUbaCartesPrepayees);
        commissionsEntity.setCommissionsUbaDepotRetrait(this.commissionsUbaDepotRetrait);

        return commissionsEntity;
    }
}
