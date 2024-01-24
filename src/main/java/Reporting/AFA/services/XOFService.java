package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.Repository.XOFRepository;
import Reporting.AFA.dto.XOFDto;
import Reporting.AFA.Entity.XOF;

import java.util.List;

@Service
public class XOFService {
    private static final Logger logger = LoggerFactory.getLogger(XOFService.class);

    @Autowired
    private XOFRepository xofRepository;

    public XOF toEntity(XOFDto xofDto) {
        XOF xof = new XOF();
        xof.setBilletDixMille(xofDto.getBilletDixMille());
        xof.setBilletCinqMille(xofDto.getBilletCinqMille());
        xof.setBilletDeuxMille(xofDto.getBilletDeuxMille());
        xof.setBilletMille(xofDto.getBilletMille());
        xof.setBilletCinqCent(xofDto.getBilletCinqCent());
        xof.setPieceCinqCent(xofDto.getPieceCinqCent());
        xof.setPieceDeuxCent(xofDto.getPieceDeuxCent());
        xof.setPieceCent(xofDto.getPieceCent());
        xof.setPieceCinquante(xofDto.getPieceCinquante());
        xof.setPieceVingtCinq(xofDto.getPieceVingtCinq());
        xof.setPieceDix(xofDto.getPieceDix());
        xof.setPieceCinq(xofDto.getPieceCinq());

        return xof;
    }

    public XOF saveXOF(XOF xof) {
        return xofRepository.save(xof);
    }

    public List<XOF> getAllXOF() {
        return xofRepository.findAll();
    }

    public XOF getXOFById(Long xofId) {
        return xofRepository.findById(xofId).orElse(null);
    }


}
