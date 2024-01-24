package Reporting.AFA.services;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Repository.AgentRepository;
import Reporting.AFA.Security.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Repository.CaisseXOFRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CaisseXOFService {

    private static final Logger logger = LoggerFactory.getLogger(CaisseService.class);

    private final CaisseXOFRepository caisseXOFRepository;
    private final AgentRepository agentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CaisseXOFService(CaisseXOFRepository caisseXOFRepository, AgentRepository agentRepository,UserRepository userRepository) {
        this.caisseXOFRepository = caisseXOFRepository;
        this.agentRepository = agentRepository;
        this.userRepository=userRepository;
    }



    @Transactional

    public CaisseXOF saisirCaisse(XOF xof, Agent agent, String natureCaisse) {
        CaisseXOF caisse=new CaisseXOF();
        caisse.setXof(xof);
        caisse.setAgent(agent);
        caisse.setNatureCaisse(natureCaisse);
        caisse.setDateCreation(new Date());
        caisse.calculerMontantTotal();
        return caisseXOFRepository.save(caisse);
    }

    public List<CaisseXOF> getAllCaisse() {
        return caisseXOFRepository.findAll();
    }

    public CaisseXOF getCaisseById(Long caisseId) {
        return caisseXOFRepository.findById(caisseId).orElse(null);
    }

    @Transactional
    public CaisseXOF updateCaisse(Long caisseId, XOF xof, Agent agent) {
        Optional<CaisseXOF> optionalCaisse = caisseXOFRepository.findById(caisseId);

        if (optionalCaisse.isPresent()) {
            CaisseXOF caisse = optionalCaisse.get();
            // Mise à jour de la date
            //caisse.setDateCreation(new Date());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            // Créez la date actuelle
            Date currentDate = new Date();

            // Formatez la date selon le format spécifié
            String formattedDate = dateFormat.format(currentDate);
            try {
                caisse.setDateCreation(dateFormat.parse(formattedDate));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            caisse.setXof(xof);


            caisse.setAgent(agent);

            // Recalculer le montant total après la mise à jour des euros
            caisse.calculerMontantTotal();

            // Enregistrez les modifications dans la base de données
            return caisseXOFRepository.save(caisse);
        } else {
            throw new RuntimeException("Caisse not found with id: " + caisseId);
        }
    }


    public void deleteCaisse(Long caisseId) {
        caisseXOFRepository.deleteById(caisseId);
    }

    public Agent findAgentByUsername(String username) {
        AppUser appUser = userRepository.findByUsername(username);
        return agentRepository.findByUser(appUser);
    }

    public Agent findAgentByUserId(String userId) {
        Optional<Agent> agent = Optional.ofNullable(agentRepository.findAgentByUserId(userId));
        return agent.orElseThrow(() -> new RuntimeException("Agent not found with user id: " + userId));
    }
}



