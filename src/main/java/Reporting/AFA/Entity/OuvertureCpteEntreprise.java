package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class OuvertureCpteEntreprise {
    @Id
    private String id;

    private Date date;

    private String agent;

    // Attributs spécifiques à OuvertureCpteEntreprise
    private String typeCompte;

    @Enumerated(EnumType.STRING)
    private FormeJuridique formeJuridique;

    private String rccm;

    private String ninea;

    private String denominationSocial;

    @Enumerated(EnumType.STRING)
    private Pack pack;

    private double montantDepotInitial;

    private String nom;

    private String prenom;

    private String fonctions;

    private String numCNI_Passeport;

    private String numTelephone;

    private String adresse;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public OuvertureCpteEntreprise() {
        this.id = generateId();
        this.date = new Date();
        this.typeCompte = "Compte_Courant";
    }

    // Enumérations
    public enum FormeJuridique {
        EI, SA, SARL, SAS, GIE, Association
    }

    public enum Pack {
        Terru, Doolel, YaatalTPE, Ndariñ_microentreprises, AND_Jappo_GIE_TAMBALI
    }
}
