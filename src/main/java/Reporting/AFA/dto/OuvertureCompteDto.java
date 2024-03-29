package Reporting.AFA.dto;

import Reporting.AFA.Entity.OuvertureCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OuvertureCompteDto{
        private OuvertureCompte.TypeCompte typeCompte;
        private String civilite;
        private String nom;
        private String prenom;
        private String numTel;
        private String adresse;
        private String activite;
        private String numCni_Passeport;
        private String nomConjoint_Conjointe;
        private String nom_PrenomMere;
        private OuvertureCompte.Pack pack;
        private double montantDepotInitial;

        public OuvertureCompte toEntity() {
            OuvertureCompte ouvertureCompte = new OuvertureCompte();
            ouvertureCompte.setTypeCompte(this.typeCompte);
            ouvertureCompte.setCivilite(OuvertureCompte.Civilite.valueOf(civilite));
            ouvertureCompte.setNom(nom);
            ouvertureCompte.setPrenom(prenom);
            ouvertureCompte.setNumTel(numTel);
            ouvertureCompte.setAdresse(adresse);
            ouvertureCompte.setActivite(activite);
            ouvertureCompte.setNumCni_Passeport(numCni_Passeport);
            ouvertureCompte.setNomConjoint_Conjointe(nomConjoint_Conjointe);
            ouvertureCompte.setNom_PrenomMere(nom_PrenomMere);
            ouvertureCompte.setPack(this.pack);
            ouvertureCompte.setMontantDepotInitial(montantDepotInitial);
            return ouvertureCompte;
        }
}
