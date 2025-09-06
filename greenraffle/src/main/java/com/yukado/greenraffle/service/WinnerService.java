package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.WinnerDto;
import com.yukado.greenraffle.model.Winner;

import java.util.List;

public interface WinnerService {
    Winner save(Winner winner);
    Winner save(WinnerDto winnerDto);
    Winner findByRaffleid(Long raffleid);
    List<Winner> findAll();
}
