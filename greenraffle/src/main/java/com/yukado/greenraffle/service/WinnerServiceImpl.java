package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.WinnerDto;
import com.yukado.greenraffle.model.Winner;
import com.yukado.greenraffle.repo.WinnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinnerServiceImpl implements WinnerService {

    @Autowired
    private WinnerRepo winnerRepo;

    @Override
    public Winner save(Winner winner) {
        // Zusätzliche Business-Logik oder Validierungen hier
        return winnerRepo.save(winner);
    }

    @Override
    public Winner save(WinnerDto winnerDto) {
        Winner winner = new Winner();
        return winnerRepo.save(winner);
    }

    @Override
    public Winner findByRaffleid(Long raffleid) {
        return winnerRepo.findByRaffleid(raffleid);
    }

    @Override
    public List<Winner> findAll() {
        return winnerRepo.findAll();
    }
}
