package com.yukado.greenraffle.repo;

import com.yukado.greenraffle.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepo extends CrudRepository<Ticket, Long> {

    List<Ticket> findAll();
    Ticket findTicketById(Long id);

    // Ermittelt Tickets, die zwischen now und now+5 Stunden enden
    @Query("SELECT t FROM Ticket t WHERE t.endDateTime BETWEEN :now AND :end")
    List<Ticket> findTicketsEndingBetween(@Param("now") LocalDateTime now, @Param("end") LocalDateTime end);

    List<Ticket> findByEndDateTimeBefore(LocalDateTime now);
    List<Ticket> findByEndDateTimeBeforeAndWinner(LocalDateTime now, String winner);

}
