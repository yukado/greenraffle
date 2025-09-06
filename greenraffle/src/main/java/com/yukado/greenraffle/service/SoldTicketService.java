package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.SoldTicketDto;
import com.yukado.greenraffle.model.SoldTicket;
import com.yukado.greenraffle.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface SoldTicketService {

    SoldTicket save(SoldTicketDto soldTicketDto);
    SoldTicket save(SoldTicket soldTicket);
    Optional<SoldTicket> findById(Long id);
    List<SoldTicket> findAll();
    List<String> getDistinctCitynamesFromSoldTickets();
    List <SoldTicket> findByUserId(Long userId);

    /**
     * Registriert verkaufte Tickets für ein existierendes Ticket.
     *
     * @param ticket                Das Ticket, zu dem verkaufte Exemplare registriert werden.
     * @param purchaseQuantity      Die Anzahl der verkauften Tickets.
     * @param userId              Der Name des Käufers (hier z.B. ein Admin).
     * @param paymethod             Die gewählte Zahlungsart.
     * @param availableBeforePurchase Die verfügbare Menge vor dem Kauf – für die Berechnung der Losnummer.
     */
    void saveSoldTickets(Ticket ticket, Long purchaseQuantity, Long userId, String paymethod, long availableBeforePurchase);
}
