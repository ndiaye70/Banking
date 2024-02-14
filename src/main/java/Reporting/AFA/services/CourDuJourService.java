package Reporting.AFA.services;
import Reporting.AFA.Entity.CourDuJour;
import Reporting.AFA.Repository.CourDuJourRepository;
import Reporting.AFA.dto.CourDuJourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourDuJourService {

    private final CourDuJourRepository courDuJourRepository;

    @Autowired
    public CourDuJourService(CourDuJourRepository courDuJourRepository) {
        this.courDuJourRepository = courDuJourRepository;
    }

    public List<CourDuJour> getAllCourDuJour() {
        return courDuJourRepository.findAll();
    }

    public Optional<CourDuJour> getCourDuJourById(Long id) {
        return courDuJourRepository.findById(id);
    }

    public CourDuJour updateExchange(Long courdujourId, CourDuJour courDuJour) {
        Optional<CourDuJour> optionalCourDuJour = courDuJourRepository.findById(courdujourId);

        if (optionalCourDuJour.isPresent()) {
            CourDuJour courDuJour1 = optionalCourDuJour.get();
            courDuJour1.setAchatEuro(courDuJour.getAchatEuro());
            courDuJour1.setAchatUSD(courDuJour.getAchatUSD());
            courDuJour1.setVenteEuro(courDuJour.getVenteEuro());
            courDuJour1.setVenteUSD(courDuJour.getVenteUSD());

            return courDuJourRepository.save(courDuJour1);

        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Operations with ID " +courdujourId + " not found");
        }
    }

    public CourDuJour saveCourDuJour(CourDuJourDto courDuJourDto) {
        CourDuJour courDuJour=courDuJourDto.toEntity();
        return courDuJourRepository.save(courDuJour);
    }

    public void deleteCourDuJour(Long id) {
        courDuJourRepository.deleteById(id);
    }
}



