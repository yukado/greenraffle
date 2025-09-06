package com.yukado.greenraffle.controller;

import com.yukado.greenraffle.model.Cart;
import com.yukado.greenraffle.model.Ticket;
import com.yukado.greenraffle.service.CartService;
import com.yukado.greenraffle.service.PaymentService;
import com.yukado.greenraffle.service.SoldTicketService;
import com.yukado.greenraffle.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SoldTicketService soldTicketService;

    @PostMapping("/user/checkout")
    public String checkout(@RequestParam("paymentMethod") String paymentMethod, @RequestParam("userId") Long userId, Model model) {
        // Hole den aktuellen Warenkorb des Benutzers (die Methode kann je nach Implementierung variieren)
        List<Cart> cart = cartService.findByUserId(userId);

        if (cart.isEmpty()) {
            model.addAttribute("error", "Warenkorb ist leer!");
            return "/user/cart"; // Zeige die cart.html erneut
        }

        try {
            // Zahlung verarbeiten
            boolean paymentSuccess = paymentService.processPayment(paymentMethod, cart);

            if (!paymentSuccess) {
                model.addAttribute("error", "Bezahlung fehlgeschlagen. Bitte versuche es erneut!");
                return "/user/cart"; // Bei fehlgeschlagener Zahlung zurück zum Warenkorb
            }

            // Für jedes Cart-Item: Ticketbestand aktualisieren & verkaufte Tickets speichern
            for (Cart item : cart) {
                // Hole das Ticket, das dem Cart-Item zugeordnet ist
                Ticket ticket = ticketService.findTicketById(item.getRaffleId());

                // Speichere den ursprünglichen Bestand fuer die Berechnung der Losnummern
                long availableBeforePurchase = ticket.getQuantity();

                // Aktualisiere den Ticket-Bestand (Methode prüft, ob genügend Tickets vorhanden sind)
                ticket = ticketService.purchaseTicket(item.getRaffleId(), item.getQuantity());

                // Speichere die verkauften Tickets, z. B. mit Admin oder dem aktuellen Benutzernamen
                soldTicketService.saveSoldTickets(ticket, item.getQuantity(), item.getUserId(), paymentMethod, availableBeforePurchase);
            }

            // Warenkorb leeren, nachdem der Kauf erfolgreich abgeschlossen wurde
            cartService.deleteByUserId(userId);

            // Umleiten auf die Seite /user/myraffles im Erfolgsfall
            return "redirect:/user/myraffles";
        } catch (Exception e) {
            model.addAttribute("error", "Fehler beim Bezahlvorgang: " + e.getMessage());
            return "cart"; // Bei Fehler: Zurück zum Warenkorb
        }
    }
}
