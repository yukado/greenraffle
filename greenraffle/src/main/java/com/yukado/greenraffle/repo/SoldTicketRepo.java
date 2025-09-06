package com.yukado.greenraffle.repo;

import com.yukado.greenraffle.model.SoldTicket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoldTicketRepo extends CrudRepository<SoldTicket, Long> {

    List<SoldTicket> findAll();

    Optional<SoldTicket> findById(Long id);

    // Liefert distinct Städte (cityname) aus den verkauften Tickets
    @Query("SELECT DISTINCT s.cityname FROM SoldTicket s")
    List<String> findDistinctCitynamesFromSoldTickets();

    // Liefert das erste SoldTicket, das zur übergebenen raffleId passt
    SoldTicket findFirstByRaffleId(Long raffleId);
    List<SoldTicket> findAllByRaffleId(Long raffleId);
    List<SoldTicket> findByUserId(Long userId);
}
