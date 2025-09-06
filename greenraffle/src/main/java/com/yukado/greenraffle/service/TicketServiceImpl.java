package com.yukado.greenraffle.service;

import com.yukado.greenraffle.model.SoldTicket;
import com.yukado.greenraffle.model.User;
import com.yukado.greenraffle.repo.SoldTicketRepo;
import com.yukado.greenraffle.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.yukado.greenraffle.model.Ticket;
import com.yukado.greenraffle.dto.TicketDto;
import java.util.Locale;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private SoldTicketRepo soldTicketRepo;
    @Autowired
    @Lazy
    private  TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private  EmailService emailService;
    @Autowired
    MessageSource messageSource;


    @Override
    public Ticket save(TicketDto ticketDto) {
        Ticket ticket = new Ticket(ticketDto.getCityname(), ticketDto.getPrice(),
                ticketDto.getWinamount(), ticketDto.getQuantity(), ticketDto.getStartquantity(),
                ticketDto.getStartDateTime(), ticketDto.getEndDateTime(), ticketDto.getDescription(),
                ticketDto.getWinner(), ticketDto.getWinnerticket());
        return ticketRepo.save(ticket);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    @Override
    public void deleteById(Long productId) {

    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepo.findTicketById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }

    @Override
    public void updateTicketById(Long id) {

    }

    @Override
    public List<Ticket> getUpcomingTickets() {
        return List.of();
    }

    @Override
    public Ticket purchaseTicket(Long ticketId, Long quantity) {
        Ticket ticket = ticketRepo.findTicketById(ticketId);

        if (ticket.getQuantity() < quantity) {
            throw new RuntimeException("Nicht genügend Tickets verfügbar");
        }

        ticket.setQuantity(ticket.getQuantity() - quantity);
        return ticketRepo.save(ticket);
    }
    @Override
    public List<Ticket> findAllByEndDateTime() {
        // Alle Tickets abrufen, deren endDateTime vor "jetzt" liegt
        return ticketRepo.findByEndDateTimeBefore(LocalDateTime.now());
    }

    @Override
    public List<Ticket> findExpiredTicketsWithNoWinner() {
        // Hol alle Tickets, bei denen endDateTime in der Vergangenheit liegt und winner "No" ist
        return ticketRepo.findByEndDateTimeBeforeAndWinner(LocalDateTime.now(), "No");
    }

    @Override
    public void autoSelectWinner() {
        List<Ticket> expiredTickets = findExpiredTicketsWithNoWinner();
        for (Ticket ticket : expiredTickets) {
            // ... innerhalb deines Loops über alle abgelaufenen Tickets:
            List<SoldTicket> soldTickets = soldTicketRepo.findAllByRaffleId(ticket.getId());
            if (soldTickets != null && !soldTickets.isEmpty()) {
                // Erzeuge eine Instanz von Random
                Random random = new Random();
                // Wähle einen zufälligen Index zwischen 0 (inklusive) und der Anzahl der verkauften Lose (exklusive)
                int randomIndex = random.nextInt(soldTickets.size());

                // Hole das zufällig ausgewählte SoldTicket
                SoldTicket selectedTicket = soldTickets.get(randomIndex);

                // Nehme die benötigten Daten
                Long selectedTicketId = selectedTicket.getTicketId();
                Long selectedUserId = selectedTicket.getUserId(); // oder getUserId(), falls du eine numerische ID hast
                User user = userService.findById(selectedUserId);

                // Falls die Ticket-ID auf das Gewinn-Ticket verweist, hole das Ticket aus der DB:
                Ticket winningTicket = ticketService.findTicketById(selectedTicketId);
                double win = (winningTicket.getStartquantity() - winningTicket.getQuantity()) * winningTicket.getPrice();
                win = win * 0.95;
                double diff = winningTicket.getStartquantity() - winningTicket.getQuantity();
                // Setze das winner-Feld; hier als Beispiel "U" + userId + ticketId
                winningTicket.setWinner("U" + selectedUserId + "Los Nr.: " + selectedTicketId);
                // Speichere das aktualisierte Ticket
                ticketService.save(winningTicket);

                String empiricalEmail = user.getEmail();
                String lang = user.getLang();
                Locale locale = new Locale(lang);
                String subject = messageSource.getMessage("email.win.subject", null, locale);
                String text = "Sehr geehrte/r " + user.getVorname() + " " + user.getNachname() + ",\n" +
                        "\n bei der Green Raffle Verlosung " + winningTicket.getCityname() + winningTicket.getId() + " ist Ihr Los mit der Nummer: !" + winningTicket.getId() + "das" +
                        " Gewinnerlos. Sie haben nicht den gesamten Gewinnbetrag gewonnen. Da die Zeit abgelaufen war, bevor alle Lose verkauft wurden, ergibt sich ihr Gewinn" +
                        " von " + win + "€ aus Anzahl der Verkauften Lose und deren Preis, abzüglich der Spende von 5%. In diesem Fall wurden " + diff + " Lose a " +
                        winningTicket.getPrice() + "€ verkauft. Weitere Details finden Sie bei Green Raffle.\n Vielen Dank fürs mitspielen! \n Green Raffle Team";
                emailService.sendRegistrationEmail(empiricalEmail, subject, text);

            }
        }
    }
}
