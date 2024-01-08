package Reporting.AFA.services;

        import Reporting.AFA.Repository.OuvertureCompteRepository;
        import Reporting.AFA.dto.OuvertureCompteDto;
        import Reporting.AFA.Entity.OuvertureCompte;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
public class OuvertureCompteService {

    private final OuvertureCompteRepository ouvertureCompteRepository;

    @Autowired
    public OuvertureCompteService(OuvertureCompteRepository ouvertureCompteRepository) {
        this.ouvertureCompteRepository = ouvertureCompteRepository;
    }

    public OuvertureCompte saveOuvertureCompte(OuvertureCompteDto ouvertureCompteDto) {
        OuvertureCompte ouvertureCompte = ouvertureCompteDto.toEntity();
        return ouvertureCompteRepository.save(ouvertureCompte);
    }

    public List<OuvertureCompte> getAllOuvertureComptes() {
        return ouvertureCompteRepository.findAll();
    }

    public Optional<OuvertureCompte> getOuvertureCompteById(String ouvertureCompteId) {
        return ouvertureCompteRepository.findById(ouvertureCompteId);
    }

    public void deleteOuvertureCompteById(String ouvertureCompteId) {
        ouvertureCompteRepository.deleteById(ouvertureCompteId);
    }
}

