package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.Carte;
import Reporting.AFA.Repository.CarteRepository;
import Reporting.AFA.dto.CarteDto;
import Reporting.AFA.dto.CustomCarte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarteService {

    private final CarteRepository carteRepository;

    @Autowired
    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public Carte saveCarte(CarteDto carteDto, Agent agent) {
        Carte carte = carteDto.toEntity();
        carte.setAgent(agent);
        return carteRepository.save(carte);
    }

    public List<CustomCarte> getCustomCartes() {
        List<Object[]> results = carteRepository.getCustomCarte();
        return results.stream().map(this::mapToCustomCarteResult).collect(Collectors.toList());
    }

    private CustomCarte mapToCustomCarteResult(Object[] result) {
        CustomCarte customCarteResult = new CustomCarte();
        // Mapping des attributs depuis le résultat de la requête
        customCarteResult.setId((String) result[0]);
        customCarteResult.setDate((String) result[1]);
        customCarteResult.setAgent((String) result[2]);
        customCarteResult.setNomComplet((String) result[3]);
        customCarteResult.setTel((String) result[4]);
        customCarteResult.setService(CustomCarte.Service.valueOf((String) result[5]));
        customCarteResult.setMontant((Double) result[6]);
        return customCarteResult;
    }


    public List<Carte> getAllCartes() {
        return carteRepository.findAll();
    }

    public Optional<Carte> getCarteById(String carteId) {
        return carteRepository.findById(carteId);
    }

    public void deleteCarteById(String carteId) {
        carteRepository.deleteById(carteId);
    }

    public Carte updateCarte(String carteId, Carte carte) {
        Optional<Carte> existingCarteOptional = carteRepository.findById(carteId);
        if (existingCarteOptional.isPresent()) {
            Carte existingCarte = existingCarteOptional.get();
            // Mettre à jour les attributs
            existingCarte.setDate(carte.getDate());
            existingCarte.setNomComplet(carte.getNomComplet());
            existingCarte.setTel(carte.getTel());
            existingCarte.setService(carte.getService());
            existingCarte.setMontant(carte.getService().getMontant());
            existingCarte.setAgent(carte.getAgent());
            // Sauvegarder la carte mise à jour
            return carteRepository.save(existingCarte);
        } else {
            // Gérer le cas où la carte avec l'ID donné n'est pas trouvée
            throw new RuntimeException("Carte with ID " + carteId + " not found");
        }
    }

    // Ajouter d'autres méthodes de service personnalisées au besoin
}

