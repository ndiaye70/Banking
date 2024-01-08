package Reporting.AFA.services;

import Reporting.AFA.Repository.ApprovisionnementRepository;
import Reporting.AFA.dto.ApprovisionnementDto;
import Reporting.AFA.Entity.Approvisionnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovisionnementService {

    private final ApprovisionnementRepository approvisionnementRepository;

    @Autowired
    public ApprovisionnementService(ApprovisionnementRepository approvisionnementRepository) {
        this.approvisionnementRepository = approvisionnementRepository;
    }

    public Approvisionnement saveApprovisionnement(ApprovisionnementDto approvisionnementDto) {
        Approvisionnement approvisionnement = approvisionnementDto.toEntity();
        return approvisionnementRepository.save(approvisionnement);
    }

    public List<Approvisionnement> getAllApprovisionnements() {
        return approvisionnementRepository.findAll();
    }

    public Optional<Approvisionnement> getApprovisionnementById(String approvisionnementId) {
        return approvisionnementRepository.findById(approvisionnementId);
    }

    // Ajoutez d'autres méthodes de service au besoin
}
