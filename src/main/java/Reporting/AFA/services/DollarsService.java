package Reporting.AFA.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Repository.DollarsRepository;
import Reporting.AFA.dto.DollarsDto;
import Reporting.AFA.Entity.Dollars;

import java.util.List;

@Service
public class DollarsService {
    private static final Logger logger = LoggerFactory.getLogger(DollarsService.class);

    @Autowired
    private DollarsRepository dollarsRepository;

    public Dollars toEntity(DollarsDto dollarsDto) {
        Dollars dollars = new Dollars();
        dollars.setBilletUnDollar(dollarsDto.getBilletUnDollar());
        dollars.setBilletDeuxDollars(dollarsDto.getBilletDeuxDollars());
        dollars.setBilletCinqDollars(dollarsDto.getBilletCinqDollars());
        dollars.setBilletDixDollars(dollarsDto.getBilletDixDollars());
        dollars.setBilletVingtDollars(dollarsDto.getBilletVingtDollars());
        dollars.setBilletCinquanteDollars(dollarsDto.getBilletCinquanteDollars());
        dollars.setBilletCentDollars(dollarsDto.getBilletCentDollars());
        // Ajoutez d'autres attributs selon les besoins

        return dollars;
    }


    public Dollars saveDollars(Dollars dollars) {
        return dollarsRepository.save(dollars);
    }

    public List<Dollars> getAllDollars() {
        return dollarsRepository.findAll();
    }

    public Dollars getDollarsById(Long dollarsId) {
        return dollarsRepository.findById(dollarsId).orElse(null);
    }
}
