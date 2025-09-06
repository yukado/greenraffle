package com.yukado.greenraffle.controller;

import com.yukado.greenraffle.dto.CartDto;
import com.yukado.greenraffle.dto.UserDto;
import com.yukado.greenraffle.model.Ticket;
import com.yukado.greenraffle.model.User;
import com.yukado.greenraffle.model.Winner;
import com.yukado.greenraffle.service.EmailService;
import com.yukado.greenraffle.service.TicketService;
import com.yukado.greenraffle.service.UserService;
import com.yukado.greenraffle.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private WinnerService winnerService;
    @Autowired
    private EmailService emailService;

    // Leitet den Root-Endpunkt auf /standart weiter
    @GetMapping("/")
    public String redirectToStandart() {
        return "index";
    }

    @GetMapping("/about")
    public String ueberUns(Model model) {
        // Falls du dynamische Inhalte für die "Über uns"-Seite hast, füge sie dem Model hinzu.
        return "about";  // corresponding to about.html
    }
    // Zeigt die Login-Seite
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto, Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserDto());
        }
        return "/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes) {
        userDto.setRole("USER");
        userService.save(userDto);

        String empiricalEmail = userDto.getEmail();
        String subject = "Registrierung bei Green Raffle erfolgreich";
        String text = "Hallo, Deine Registrierung war erfolgreich. Willkommen bei uns!";

        boolean emailSent = emailService.sendRegistrationEmail(empiricalEmail, subject, text);

        if (emailSent) {
            redirectAttributes.addFlashAttribute("emailStatus", "Die Bestätigungsemail wurde erfolgreich versendet.");
        } else {
            redirectAttributes.addFlashAttribute("emailStatus", "Beim Versand der E-Mail ist ein Fehler aufgetreten.");
        }
        redirectAttributes.addFlashAttribute("message", "Registrierung erfolgreich!");
        return "redirect:/login";
    }


    @GetMapping("/raffles")
    public String raffles(Model model) {
        // Alle Tickets abrufen
        List<Ticket> tickets = ticketService.findAll();
        List<Map<String, Object>> ticketData = new ArrayList<>();

        // Aktueller Zeitpunkt
        LocalDateTime now = LocalDateTime.now();

        for (Ticket ticket : tickets) {
            Map<String, Object> data = new HashMap<>();
            data.put("ticket", ticket);

            if (ticket.getEndDateTime() != null) {
                Duration duration = Duration.between(now, ticket.getEndDateTime());
                if (duration.isNegative()) {
                    duration = Duration.ZERO;
                }
                long remainingSeconds = duration.getSeconds();
                data.put("remainingSeconds", remainingSeconds);
            } else {
                data.put("remainingSeconds", 0);
            }

            ticketData.add(data);
        }

        model.addAttribute("ticketData", ticketData);
        return "raffles";  // dein Template z. B. raffles.html
    }
    @GetMapping("/ticket/{ticketId}")
    public String getTicket(@PathVariable("ticketId") Long id, Model model) {
        Ticket ticket = ticketService.findTicketById(id);
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> ticketData = new HashMap<>();
        ticketData.put("ticket", ticket);

        if (ticket.getEndDateTime() != null) {
            Duration duration = Duration.between(now, ticket.getEndDateTime());
            if (duration.isNegative()) {
                duration = Duration.ZERO;
            }
            long remainingSeconds = duration.getSeconds();
            ticketData.put("remainingSeconds", remainingSeconds);
        } else {
            ticketData.put("remainingSeconds", 0);
        }
        model.addAttribute("ticketData", ticketData);

        return "ticket";
    }

    @GetMapping("/winnerstable")
    public String showWinner(Model model) {
        List<Winner> winner = winnerService.findAll();
        model.addAttribute("winner", winner);
        return "winnerstable";
    }
    @GetMapping("/finished")
    public String showFinished(Model model) {
        // Alle Tickets abrufen
        List<Ticket> tickets = ticketService.findAll();
        List<Map<String, Object>> ticketData = new ArrayList<>();

        // Aktueller Zeitpunkt
        LocalDateTime now = LocalDateTime.now();

        for (Ticket ticket : tickets) {
            Map<String, Object> data = new HashMap<>();
            data.put("ticket", ticket);

            if (ticket.getEndDateTime() != null) {
                Duration duration = Duration.between(now, ticket.getEndDateTime());
                if (duration.isNegative()) {
                    duration = Duration.ZERO;
                }
                long remainingSeconds = duration.getSeconds();
                data.put("remainingSeconds", remainingSeconds);
            } else {
                data.put("remainingSeconds", 0);
            }

            ticketData.add(data);
        }

        model.addAttribute("ticketData", ticketData);
        return "finished";
    }
}

