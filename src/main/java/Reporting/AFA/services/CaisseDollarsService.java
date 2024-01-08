package Reporting.AFA.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Repository.CaisseDollarsRepository;
import Reporting.AFA.Entity.CaisseDollars;

import java.util.List;

@Service
public class CaisseDollarsService {
    private static final Logger logger = LoggerFactory.getLogger(CaisseDollarsService.class);

    @Autowired
    private CaisseDollarsRepository caisseDollarsRepository;

    @Autowired
    private DollarsService dollarsService;



    public CaisseDollars saveCaisseDollars(CaisseDollars caisseDollars) {
        return caisseDollarsRepository.save(caisseDollars);
    }

    public List<CaisseDollars> getAllCaisseDollars() {
        return caisseDollarsRepository.findAll();
    }

    public CaisseDollars getCaisseDollarsById(Long caisseDollarsId) {
        return caisseDollarsRepository.findById(caisseDollarsId).orElse(null);
    }
}
