package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Repository.OuvertureCompteRepository;
import Reporting.AFA.dto.CustomCompteResult;
import Reporting.AFA.dto.OuvertureCompteDto;
import Reporting.AFA.Entity.OuvertureCompte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OuvertureCompteService {

    private final OuvertureCompteRepository ouvertureCompteRepository;

    @Autowired
    public OuvertureCompteService(OuvertureCompteRepository ouvertureCompteRepository) {
        this.ouvertureCompteRepository = ouvertureCompteRepository;
    }

    public OuvertureCompte saveOuvertureCompte(OuvertureCompteDto ouvertureCompteDto, Agent agent) {
        OuvertureCompte ouvertureCompte = ouvertureCompteDto.toEntity();
        ouvertureCompte.setAgent(agent);
        return ouvertureCompteRepository.save(ouvertureCompte);
    }

    public OuvertureCompte updateCompte(String compteId, OuvertureCompte ouvertureCompte) {
        Optional<OuvertureCompte> compteOptional=ouvertureCompteRepository.findById(compteId);

        if (compteOptional.isPresent()) {
            OuvertureCompte compte=compteOptional.get();

            // Copy the updated values to the existing entity
            compte.setDate(ouvertureCompte.getDate());
            compte.setAgent(ouvertureCompte.getAgent());
            compte.setCivilite(ouvertureCompte.getCivilite());
            compte.setNom(ouvertureCompte.getNom());
            compte.setPrenom(ouvertureCompte.getPrenom());
            compte.setNumTel(ouvertureCompte.getNumTel());
            compte.setTypeCompte(ouvertureCompte.getTypeCompte());
            compte.setAdresse(ouvertureCompte.getAdresse());
            compte.setActivite(ouvertureCompte.getActivite());
            compte.setNumCni_Passeport(ouvertureCompte.getNumCni_Passeport());
            compte.setNomConjoint_Conjointe(ouvertureCompte.getNomConjoint_Conjointe());
            compte.setNom_PrenomMere(ouvertureCompte.getNom_PrenomMere());
            compte.setPack(ouvertureCompte.getPack());
            compte.setMontantDepotInitial(ouvertureCompte.getMontantDepotInitial());
            // Save the updated entity
            return ouvertureCompteRepository.save(compte);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Compte with ID " +compteId + " not found");
        }
    }


    public List<CustomCompteResult> getCustomCompte() {
        List<Object[]> results = ouvertureCompteRepository.getCustomCompte();
        return results.stream().map(this::mapToCustomCompteResult).collect(Collectors.toList());
    }

    private CustomCompteResult mapToCustomCompteResult(Object[] result) {
        CustomCompteResult customCompteResult=new CustomCompteResult();
        customCompteResult.setId((String) result[0]);
        customCompteResult.setCaissier((String) result[1]);
        customCompteResult.setDate((String) result[2]);
        customCompteResult.setTypeCompte(CustomCompteResult.TypeCompte.valueOf((String) result[3]));
        customCompteResult.setNom((String) result[4]);
        customCompteResult.setPrenom((String) result[5]);
        customCompteResult.setNumTel((String) result[6]);
        customCompteResult.setAdresse((String) result[7]);
        customCompteResult.setActivite((String) result[8]);
        customCompteResult.setNumCni_Passeport((String) result[9]);
        customCompteResult.setNomConjoint_Conjointe((String) result[10]);
        customCompteResult.setNom_PrenomMere((String) result[11]);
        customCompteResult.setPack(CustomCompteResult.Pack.valueOf((String) result[12]));
        customCompteResult.setMontantDepotInitial((Double) result[13]);
        return customCompteResult;
    }



    public Optional<OuvertureCompte> getOuvertureCompteById(String ouvertureCompteId) {
        return ouvertureCompteRepository.findById(ouvertureCompteId);
    }

    public void deleteOuvertureCompteById(String ouvertureCompteId) {
        ouvertureCompteRepository.deleteById(ouvertureCompteId);
    }
}

