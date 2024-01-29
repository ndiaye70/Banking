package Reporting.AFA.services;
import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.OuvertureCompte;
import Reporting.AFA.Repository.OuvertureCpteEntrepriseRepository;
import Reporting.AFA.dto.OuvertureCpteEntrepriseDto;
import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  OuvertureCpteEntrepriseService {

    private final OuvertureCpteEntrepriseRepository ouvertureCpteEntrepriseRepository;

    @Autowired
    public OuvertureCpteEntrepriseService(OuvertureCpteEntrepriseRepository ouvertureCpteEntrepriseRepository) {
        this.ouvertureCpteEntrepriseRepository = ouvertureCpteEntrepriseRepository;
    }

    public OuvertureCpteEntreprise saveOuvertureCpteEntreprise(OuvertureCpteEntrepriseDto ouvertureCpteEntrepriseDto, Agent agent) {
        OuvertureCpteEntreprise ouvertureCpteEntreprise = ouvertureCpteEntrepriseDto.convertDtoToEntity();
        ouvertureCpteEntreprise.setAgent(agent);
        return ouvertureCpteEntrepriseRepository.save(ouvertureCpteEntreprise);
    }

    public OuvertureCpteEntreprise updateCompte(String compteId, OuvertureCpteEntreprise ouvertureCompte) {
        Optional<OuvertureCpteEntreprise> cpteEntrepriseOptional=ouvertureCpteEntrepriseRepository.findById(compteId);

        if (cpteEntrepriseOptional.isPresent()) {
            OuvertureCpteEntreprise compte=cpteEntrepriseOptional.get();

            // Copy the updated values to the existing entity
            compte.setDate(ouvertureCompte.getDate());
            compte.setAgent(ouvertureCompte.getAgent());
            compte.setNom(ouvertureCompte.getNom());
            compte.setPrenom(ouvertureCompte.getPrenom());
            compte.setNumTel(ouvertureCompte.getNumTel());
            compte.setTypeCompte(ouvertureCompte.getTypeCompte());
            compte.setAdresse(ouvertureCompte.getAdresse());
            compte.setFonctions(ouvertureCompte.getFonctions());
            compte.setNumCNI_Passeport(ouvertureCompte.getNumCNI_Passeport());
            compte.setNinea(ouvertureCompte.getNinea());
            compte.setDenominationSocial(ouvertureCompte.getDenominationSocial());
            compte.setPack(ouvertureCompte.getPack());
            compte.setRccm(ouvertureCompte.getRccm());
            compte.setFormeJuridique(ouvertureCompte.getFormeJuridique());
            compte.setMontantDepotInitial(ouvertureCompte.getMontantDepotInitial());

            return ouvertureCpteEntrepriseRepository.save(compte);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Compte with ID " +compteId + " not found");
        }
    }

 



    public Optional<OuvertureCpteEntreprise> getOuvertureCpteEntrepriseById(String ouvertureCpteEntrepriseId) {
        return ouvertureCpteEntrepriseRepository.findById(ouvertureCpteEntrepriseId);
    }

    public void deleteOuvertureCpteEntrepriseById(String ouvertureCpteEntrepriseId) {
        ouvertureCpteEntrepriseRepository.deleteById(ouvertureCpteEntrepriseId);
    }


}


