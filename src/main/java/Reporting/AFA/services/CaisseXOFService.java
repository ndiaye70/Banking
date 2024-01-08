package Reporting.AFA.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Repository.CaisseXOFRepository;
import Reporting.AFA.Entity.CaisseXOF;

import java.util.List;

@Service
public class CaisseXOFService {
    private static final Logger logger = LoggerFactory.getLogger(CaisseXOFService.class);

    @Autowired
    private CaisseXOFRepository caisseXOFRepository;

    @Autowired
    private XOFService xofService;

    public CaisseXOF saveCaisseXOF(CaisseXOF caisseXOF) {
        return caisseXOFRepository.save(caisseXOF);
    }

    public List<CaisseXOF> getAllCaisseXOF() {
        return caisseXOFRepository.findAll();
    }

    public CaisseXOF getCaisseXOFById(Long caisseXOFId) {
        return caisseXOFRepository.findById(caisseXOFId).orElse(null);
    }


}
