package Reporting.AFA.dto;

import Reporting.AFA.Entity.SoldeDepart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldeDepartDto {

    private double orangeMoney;
    private double freeMoney;
    private double wave;
    private double wari;
    private double wizall;
    private double westernUnion;
    private double ria;
    private double moneygram;
    private double ecobank;
    private double smallWorld;
    private double bnb;
    private double askia;
    private double sunuAssurance;
    private double creationEntreprise;
    private double cartesComImpExp;
    private double change;
    private double grossisteBnb;
    private double bndeDepotRetrait;
    private double ouvertureCompteBnde;
    private double ubaCartesPrepayees;
    private double ubaDepotRetrait;


    public SoldeDepart toEntity() {
        SoldeDepart soldeDepart = new SoldeDepart();

        soldeDepart.setOrangeMoney(this.orangeMoney);
        soldeDepart.setFreeMoney(this.freeMoney);
        soldeDepart.setWave(this.wave);
        soldeDepart.setWizall(this.wizall);
        soldeDepart.setWesternUnion(this.westernUnion);
        soldeDepart.setRia(this.ria);
        soldeDepart.setMoneygram(this.moneygram);
        soldeDepart.setEcobank(this.ecobank);
        soldeDepart.setSmallWorld(this.smallWorld);
        soldeDepart.setBnb(this.bnb);
        soldeDepart.setAskia(this.askia);
        soldeDepart.setSunuAssurance(this.sunuAssurance);
        soldeDepart.setCreationEntreprise(this.creationEntreprise);
        soldeDepart.setCartesComImpExp(this.cartesComImpExp);
        soldeDepart.setChange(this.change);
        soldeDepart.setGrossisteBnb(this.grossisteBnb);
        soldeDepart.setBndeDepotRetrait(this.bndeDepotRetrait);
        soldeDepart.setOuvertureCompteBnde(this.ouvertureCompteBnde);
        soldeDepart.setUbaCartesPrepayees(this.ubaCartesPrepayees);
        soldeDepart.setUbaDepotRetrait(this.ubaDepotRetrait);
        return soldeDepart;
    }

}
