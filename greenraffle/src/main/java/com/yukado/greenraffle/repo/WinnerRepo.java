package com.yukado.greenraffle.repo;

import com.yukado.greenraffle.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinnerRepo extends JpaRepository<Winner, Long> {
    Winner findByRaffleid(Long raffleid);
    // Weitere Query-Methoden (z. B. Suche nach Gewinnern) können hier ergänzt werden.
    List<Winner> findAll();
}