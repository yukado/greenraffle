package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.SoldTicketDto;
import com.yukado.greenraffle.model.SoldTicket;
import com.yukado.greenraffle.model.Ticket;
import com.yukado.greenraffle.repo.SoldTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class SoldTicketServiceImpl implements SoldTicketService{

    @Autowired
    private SoldTicketRepo soldTicketRepo;

    @Override
    public SoldTicket save(SoldTicketDto soldTicketDto) {
        SoldTicket soldTicket = new SoldTicket(soldTicketDto.getTicketId(), soldTicketDto.getCityname(),
                soldTicketDto.getRaffleId(), soldTicketDto.getUserId(), soldTicketDto.getSellDateTime(),
                soldTicketDto.getWinner(), soldTicketDto.getPrice(), soldTicketDto.getWinamount(),
                soldTicketDto.getStartDateTime(),
                soldTicketDto.getEndDateTime(), soldTicketDto.getChances(), soldTicketDto.getPaymethod());
        return soldTicketRepo.save(soldTicket);
    }

    @Override
    public SoldTicket save(SoldTicket soldTicket) {
        return soldTicketRepo.save(soldTicket);
    }

    @Override
    public Optional<SoldTicket> findById(Long id) {
        return soldTicketRepo.findById(id);
    }

    @Override
    public List<SoldTicket> findAll() {
        return soldTicketRepo.findAll();
    }



    @Override
    public List<String> getDistinctCitynamesFromSoldTickets() {
        return soldTicketRepo.findDistinctCitynamesFromSoldTickets();
    }

    @Override
    public List<SoldTicket> findByUserId(Long userId) {
        return List.of();
    }

    @Override
    public void saveSoldTickets(Ticket ticket, Long purchaseQuantity, Long userId, String paymethod, long availableBeforePurchase) {
        // Hier gehen wir davon aus, dass die Losnummer aus der ursprünglich verfügbaren Anzahl abgeleitet wird.
        // Beispiel: Wenn availableBeforePurchase = 100 und purchaseQuantity = 5, dann
        // erhalten die verkauften Tickets die Losnummern 100, 99, 98, 97 und 96.
        for (int i = 0; i < purchaseQuantity; i++) {
            SoldTicket soldTicket = new SoldTicket();
            double won = (ticket.getStartquantity() - ticket.getQuantity()) * ticket.getPrice();
            // Berechnung der Losnummer
            long losnummer = availableBeforePurchase - i;
            soldTicket.setTicketId(losnummer);
            soldTicket.setCityname(ticket.getCityname());
            soldTicket.setRaffleId(ticket.getId());
            soldTicket.setUserId(userId);
            soldTicket.setSellDateTime(LocalDateTime.now());
            soldTicket.setWinner(false);
            soldTicket.setPrice(ticket.getPrice());
            soldTicket.setWinamount(ticket.getWinamount());
            soldTicket.setStartDateTime(ticket.getStartDateTime());
            soldTicket.setEndDateTime(ticket.getEndDateTime());
            soldTicket.setChances("1 : " + ticket.getStartquantity());
            soldTicket.setPaymethod(paymethod);

            soldTicketRepo.save(soldTicket);
        }
    }
}
