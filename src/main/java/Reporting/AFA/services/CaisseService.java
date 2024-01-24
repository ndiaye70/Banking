package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.Caisse;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Repository.AgentRepository;
import Reporting.AFA.Repository.CaisseRepository;
import Reporting.AFA.Security.repo.UserRepository;
import Reporting.AFA.dto.CaisseDto;
import Reporting.AFA.Entity.Euro;
import Reporting.AFA.dto.EuroDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CaisseService {

    private static final Logger logger = LoggerFactory.getLogger(CaisseService.class);

    private final CaisseRepository caisseRepository;
    private final AgentRepository agentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CaisseService(CaisseRepository caisseRepository, AgentRepository agentRepository,UserRepository userRepository) {
        this.caisseRepository = caisseRepository;
        this.agentRepository = agentRepository;
        this.userRepository=userRepository;
    }



    @Transactional

    public Caisse saisirCaisse(Euro euro, Agent agent,String natureCaisse) {
        Caisse caisse=new Caisse();
        caisse.setEuros(euro);
        caisse.setAgent(agent);
        caisse.setNatureCaisse(natureCaisse);
        caisse.setDateCreation(new Date());
        caisse.calculerMontantTotal();
        return caisseRepository.save(caisse);
    }

    public List<Caisse> getAllCaisse() {
        return caisseRepository.findAll();
    }

    public Caisse getCaisseById(Long caisseId) {
        return caisseRepository.findById(caisseId).orElse(null);
    }

    @Transactional
    public Caisse updateCaisse(Long caisseId, Euro euro, Agent agent) {
        Optional<Caisse> optionalCaisse = caisseRepository.findById(caisseId);

        if (optionalCaisse.isPresent()) {
            Caisse caisse = optionalCaisse.get();
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
            caisse.setEuros(euro);


            caisse.setAgent(agent);

            // Recalculer le montant total après la mise à jour des euros
            caisse.calculerMontantTotal();

            // Enregistrez les modifications dans la base de données
            return caisseRepository.save(caisse);
        } else {
            throw new RuntimeException("Caisse not found with id: " + caisseId);
        }
    }


    public void deleteCaisse(Long caisseId) {
        caisseRepository.deleteById(caisseId);
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
