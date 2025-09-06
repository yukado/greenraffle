package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.TicketDto;
import com.yukado.greenraffle.model.Ticket;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Ticket save(TicketDto ticketDto);
    Ticket save(Ticket ticket);
    void deleteById(Long productId);
    @Query("SELECT t FROM Ticket t WHERE t.id = ?1")
    Ticket findTicketById(Long id);
    List<Ticket> findAll();
    void updateTicketById(Long id);
    List<Ticket> getUpcomingTickets();
    Ticket purchaseTicket(Long ticketId, Long quantity);
    List<Ticket> findAllByEndDateTime();
    // Optional: Methode zur Gewinnerauswahl, damit der Controller einfach darauf zugreifen kann
    void autoSelectWinner();

    List<Ticket> findExpiredTicketsWithNoWinner();
}
