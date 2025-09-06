package com.yukado.greenraffle.controller;

import com.yukado.greenraffle.model.Ticket;
import com.yukado.greenraffle.service.SoldTicketService;
import com.yukado.greenraffle.service.TicketService;
import com.yukado.greenraffle.service.UserService;
import com.yukado.greenraffle.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class WinnerController {

    @Autowired
    SoldTicketService soldTicketService;
    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;
    @Autowired
    WinnerService winnerService;

    @PostMapping("/auto-select-winner")
    public String autoSelectWinner(Model model, Principal principal) {
        // Diese Methode sucht automatisch nach abgelaufenen Tickets ohne Gewinner,
        // wählt einen SoldTicket-Eintrag aus und aktualisiert das Ticket als Gewinner.
        ticketService.autoSelectWinner();

        // Optionale Rückgabe oder Weiterleitung (z.B. zurück zur Übersichtsseite)
        return "redirect:/admin/admin";
    }
}
