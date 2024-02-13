package Reporting.AFA.services;
import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.OuvertureCompte;
import Reporting.AFA.Repository.OuvertureCpteEntrepriseRepository;
import Reporting.AFA.dto.CustomCpteEntreprise;
import Reporting.AFA.dto.CustomOperationResult;
import Reporting.AFA.dto.OuvertureCpteEntrepriseDto;
import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  OuvertureCpteEntrepriseService {

    @Autowired
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

    public List<CustomCpteEntreprise> getCustomCpteEntreprise() {
        List<Object[]> results = ouvertureCpteEntrepriseRepository.getCustomCpteEntreprise();
        return results.stream().map(this::mapToCustomCpteEntreprise).collect(Collectors.toList());
    }
    private CustomCpteEntreprise mapToCustomCpteEntreprise(Object[] result) {
        CustomCpteEntreprise customOperationResult = new CustomCpteEntreprise();
        customOperationResult.setId((String) result[0]);
        customOperationResult.setAgent((String) result[1]);
        customOperationResult.setTypeCompte((String) result[2]);
        customOperationResult.setFormeJuridique(CustomCpteEntreprise.FormeJuridique.valueOf((String) result[3]));
        customOperationResult.setRccm((String) result[4]);
        customOperationResult.setNinea((String) result[5]);
        customOperationResult.setDenominationSocial((String) result[6]);
        customOperationResult.setPack(CustomCpteEntreprise.Pack.valueOf((String) result[7]));
        customOperationResult.setMontantDepotInitial((Double) result[8]);
        customOperationResult.setNom((String) result[9]);
        customOperationResult.setPrenom((String) result[10]);
        customOperationResult.setFonctions((String) result[11]);
        customOperationResult.setNumCNI_Passeport((String) result[12]);
        customOperationResult.setAdresse((String) result[13]);
        customOperationResult.setDate((String) result[14]);
        return customOperationResult;
    }
 



    public Optional<OuvertureCpteEntreprise> getOuvertureCpteEntrepriseById(String ouvertureCpteEntrepriseId) {
        return ouvertureCpteEntrepriseRepository.findById(ouvertureCpteEntrepriseId);
    }

    public void deleteOuvertureCpteEntrepriseById(String ouvertureCpteEntrepriseId) {
        ouvertureCpteEntrepriseRepository.deleteById(ouvertureCpteEntrepriseId);
    }


}


