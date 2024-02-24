package Reporting.AFA.services;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Repository.AgentRepository;
import Reporting.AFA.Repository.CaisseRepository;
import Reporting.AFA.Security.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Repository.CaisseDollarsRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CaisseDollarsService {

    private static final Logger logger = LoggerFactory.getLogger(CaisseService.class);

    private final CaisseDollarsRepository caisseDollarsRepository;
    private final AgentRepository agentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CaisseDollarsService(CaisseDollarsRepository caisseDollarsRepository, AgentRepository agentRepository,UserRepository userRepository) {
        this.caisseDollarsRepository = caisseDollarsRepository;
        this.agentRepository = agentRepository;
        this.userRepository=userRepository;
    }



    @Transactional

    public CaisseDollars saisirCaisse(Dollars dollars, Agent agent, String natureCaisse) {
        CaisseDollars caisse=new CaisseDollars();
        caisse.setDollars(dollars);
        caisse.setAgent(agent);
        caisse.setNatureCaisse(Caisse.NatureCaisse.valueOf(natureCaisse));
        caisse.setDateCreation(new Date());
        caisse.calculerMontantTotal();
        return caisseDollarsRepository.save(caisse);
    }

    public List<CaisseDollars> getAllCaisse() {
        return caisseDollarsRepository.findAll();
    }

    public CaisseDollars getCaisseById(Long caisseId) {
        return caisseDollarsRepository.findById(caisseId).orElse(null);
    }

    @Transactional
    public CaisseDollars updateCaisse(Long caisseId, Dollars dollars, Agent agent) {
        Optional<CaisseDollars> optionalCaisse = caisseDollarsRepository.findById(caisseId);

        if (optionalCaisse.isPresent()) {
            CaisseDollars caisse = optionalCaisse.get();
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
            caisse.setDollars(dollars);


            caisse.setAgent(agent);

            // Recalculer le montant total après la mise à jour des euros
            caisse.calculerMontantTotal();

            // Enregistrez les modifications dans la base de données
            return caisseDollarsRepository.save(caisse);
        } else {
            throw new RuntimeException("Caisse not found with id: " + caisseId);
        }
    }


    public void deleteCaisse(Long caisseId) {
        caisseDollarsRepository.deleteById(caisseId);
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
