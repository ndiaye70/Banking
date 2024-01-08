package Reporting.AFA.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Repository.EuroRepository;
import Reporting.AFA.dto.EuroDto;
import Reporting.AFA.Entity.Euro;

import java.util.List;

@Service
public class EuroService {
    private static final Logger logger = LoggerFactory.getLogger(EuroService.class);

    @Autowired
    private EuroRepository euroRepository;

    public Euro toEntity(EuroDto euroDto) {
        Euro euros = new Euro();
        euros.setBilletCinqCents(euroDto.getBilletCinqCents());
        euros.setBilletDeuxCents(euroDto.getBilletDeuxCents());
        euros.setBilletCent(euroDto.getBilletCent());
        euros.setBilletCinquante(euroDto.getBilletCinquante());
        euros.setBilletCinq(euroDto.getBilletCinq());
        euros.setBilletDix(euroDto.getBilletDix());
        euros.setBilletVingt(euroDto.getBilletVingt());

        return euros;
    }

    public Euro saveEuro(Euro euro) {
        return euroRepository.save(euro);
    }

    public List<Euro> getAllEuro() {
        return euroRepository.findAll();
    }

    public Euro getEuroById(Long euroId) {
        return euroRepository.findById(euroId).orElse(null);
    }
}
