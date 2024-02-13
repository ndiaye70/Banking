package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class OuvertureCpteEntreprise {
    @Id
    private String id;

    private String date;


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

    private String numTel;

    private String adresse;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public OuvertureCpteEntreprise() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = now.format(formatter);
        this.typeCompte = "Compte_Courant";
    }

    // Enumérations
    public enum FormeJuridique {
        EI, SA, SARL, SAS, GIE, Association
    }

    public enum Pack {
        Terru("Terru"), Doolel("Doolel"), YaatalTPE("Yaatal TPE"), Ndariñ_microentreprises("Ndariñ Microentreprises"),
        AND_Jappo_GIE_TAMBALI("AND Jappo GIE/TAMBALI");

        private final String label;

        Pack(String label) {
            this.label = label;

        }

        public String getLabel() {
            return label;
        }
    }
}
