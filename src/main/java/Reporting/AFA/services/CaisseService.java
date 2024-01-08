package Reporting.AFA.services;

import Reporting.AFA.Repository.CaisseRepository;
import Reporting.AFA.dto.CaisseDto;
import Reporting.AFA.Entity.Euro;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Entity.Caisse;

import java.util.List;

@Service
public class CaisseService {
    private static final Logger logger = LoggerFactory.getLogger(CaisseService.class);

    @Autowired
    private CaisseRepository caisseRepository;

    public Caisse convertirDtoEnCaisse(CaisseDto caisseDto) {
        Caisse caisse = new Caisse();
        Euro euros = new Euro(); // Ajoutez cette ligne
        caisse.setEuros(euros);//
        // Remplir les attributs de la classe Euro avec les quantités du DTO
        euros.setBilletCinqCents(caisseDto.getBilletCinqCents());
        euros.setBilletDeuxCents(caisseDto.getBilletDeuxCents());
        euros.setBilletCent(caisseDto.getBilletCent());
        euros.setBilletCinquante(caisseDto.getBilletCinquante());
        euros.setBilletCinq(caisseDto.getBilletCinq());
        euros.setBilletDix(caisseDto.getBilletDix());
        euros.setBilletVingt(caisseDto.getBilletVingt());

        // Vous pouvez également initialiser d'autres propriétés de la caisse si nécessaire
        // ...

        return caisse;
    }





    public Caisse saveCaisse(Caisse caisse) {
        return caisseRepository.save(caisse);
    }

    public List<Caisse> getAllCaisse() {
        return caisseRepository.findAll();
    }

    public Caisse getCaisseById(Long caisseId) {
        return caisseRepository.findById(caisseId).orElse(null);
    }



}
