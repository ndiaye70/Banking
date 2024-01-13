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

    public Caisse convertirDtoEnCaisse(CaisseDto caisseDto) {
        Caisse caisse = new Caisse();
        Euro euros = new Euro();
        caisse.setEuros(euros);
        euros.setBilletCinqCents(caisseDto.getBilletCinqCents());
        euros.setBilletDeuxCents(caisseDto.getBilletDeuxCents());
        euros.setBilletCent(caisseDto.getBilletCent());
        euros.setBilletCinquante(caisseDto.getBilletCinquante());
        euros.setBilletCinq(caisseDto.getBilletCinq());
        euros.setBilletDix(caisseDto.getBilletDix());
        euros.setBilletVingt(caisseDto.getBilletVingt());

        return caisse;
    }
    @Transactional

    public Caisse saisirCaisse(Euro euro, Agent agent) {
        Caisse caisse=new Caisse();
        caisse.setEuros(euro);
        caisse.setAgent(agent);
        caisse.setNatureCaisse("billetage");
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

    public Caisse updateCaisse(Long caisseId, CaisseDto caisseDto) {
        Optional<Caisse> optionalCaisse = caisseRepository.findById(caisseId);

        if (optionalCaisse.isPresent()) {
            Caisse caisse = optionalCaisse.get();
            Caisse updatedCaisse = convertirDtoEnCaisse(caisseDto);
            updatedCaisse.setId(caisse.getId());
            updatedCaisse.setDateCreation(caisse.getDateCreation());
            updatedCaisse.calculerMontantTotal();

            return caisseRepository.save(updatedCaisse);
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
