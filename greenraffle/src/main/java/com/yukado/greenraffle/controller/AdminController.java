package com.yukado.greenraffle.controller;

import com.yukado.greenraffle.dto.CartDto;
import com.yukado.greenraffle.dto.TicketDto;
import com.yukado.greenraffle.dto.UserDto;
import com.yukado.greenraffle.dto.WinnerDto;
import com.yukado.greenraffle.model.Ticket;
import com.yukado.greenraffle.model.User;
import com.yukado.greenraffle.service.SoldTicketService;
import com.yukado.greenraffle.service.TicketService;
import com.yukado.greenraffle.service.UserService;
import com.yukado.greenraffle.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SoldTicketService soldTicketService;

    @Autowired
    private UserService userService;

    @Autowired
    private WinnerService winnerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/admin/admin")
    public String getAdminDashboard(Model model, Principal principal) {


        // Andere Modelattribute setzen
        model.addAttribute("upcomingTickets", ticketService.getUpcomingTickets());
        model.addAttribute("soldTickets", soldTicketService.findAll());
        model.addAttribute("cities", soldTicketService.getDistinctCitynamesFromSoldTickets());

        // Security-User abrufen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();

        // Anderen Schlüssel verwenden, um Konflikte zu vermeiden:
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("userName", userName);

        return "admin/admin"; // Thymeleaf-Template z. B. admin.html
    }

    @GetMapping("/admin/index")
    public String getIndex(Model model, Principal principal) {
        // Security-User abrufen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();

        // Anderen Schlüssel verwenden, um Konflikte zu vermeiden:
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("userName", userName);

        return "admin/index"; // Thymeleaf-Template z. B. admin.html
    }

    @GetMapping("/admin/a_users")
    public String listUsers(@RequestParam(value = "search", required = false) String search,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            Model model, Principal principal) {
        Page<User> usersPage = userService.findPaginatedUsers(search, PageRequest.of(page, 100));
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("page", usersPage);
        model.addAttribute("search", search);
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("userName", userName);
        return "admin/a_users"; // Name deines Templates
    }

    @GetMapping("/admin/registration_a")
    public String adminReg(Model model, Principal principal, User user) {
        // Security-User abrufen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserDto());
        }
        // Anderen Schlüssel verwenden, um Konflikte zu vermeiden:

        model.addAttribute("currentUser", userDetails);
        model.addAttribute("userName", userName);
        return "admin/registration_a";
    }

    @PostMapping("/admin/registration_a")
    public String saveUser_a(Model model, @ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserDto());
        }
        userService.save(userDto);
        redirectAttributes.addFlashAttribute("message", "Registered Successfully!");
        return "redirect:/admin/registration_a";
    }

    @GetMapping("/admin/createticket")
    public  String showreateTicket(Model model, Principal principal) {
        // Security-User abrufen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();
        if (!model.containsAttribute("ticket")) {
            model.addAttribute("ticket", new TicketDto());
        }
        // Anderen Schlüssel verwenden, um Konflikte zu vermeiden:
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("userName", userName);
        return "admin/createticket";
    }

    @PostMapping("/admin/createticket")
    public String createTicket(@ModelAttribute("ticket") TicketDto ticketDto, RedirectAttributes redirectAttributes) {
        ticketDto.setWinner("No");
        ticketDto.setWinnerticket(0L);
        ticketDto.setStartquantity(ticketDto.getQuantity());
        ticketDto.setDescription("1:" + ticketDto.getQuantity());
        ticketService.save(ticketDto);
        redirectAttributes.addFlashAttribute("message", "Los wurde erstellt!");
        return "redirect:/admin/createticket";
    }


    @GetMapping("/admin/create_w")
    public String adminCreateWinner(Model model, Principal principal) {
        // Security-User abrufen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();
        if (!model.containsAttribute("winner")) {
            model.addAttribute("winner", new WinnerDto());
        }
        // Anderen Schlüssel verwenden, um Konflikte zu vermeiden:
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("userName", userName);
        return "admin/create_w";
    }

    @PostMapping("/admin/create_w")
    public String createWinner(@ModelAttribute("winner") WinnerDto winnerDto, Model model) {
        Ticket ticket = ticketService.findTicketById(winnerDto.getRaffleid());
        ticket.setWinner("U" + winnerDto.getUserid());
        ticket.setWinnerticket(winnerDto.getWinnerticket());
        ticketService.save(ticket);
        winnerService.save(winnerDto);
        model.addAttribute("message", "Gewinner wurde bestimmt!");
        // Anschließend zurückgeleitet zum Admin-Dashboard
        return "/admin/a_finished";
    }

    @GetMapping("/admin/a_about")
    public String showAbout(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();
        model.addAttribute("user", userDetails);
        model.addAttribute("userName", userName);
        return "/admin/about_a";
    }

    @GetMapping("/admin/a_raffles")
    public String showRaffles(Model model, Principal principal) {
        // GET-Methode für das Anzeigen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        List<Ticket> tickets = ticketService.findAll();
        List<Map<String, Object>> ticketData = new ArrayList<>();
        String userName = "A" + id1.toString();
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
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("userName", userName);
        model.addAttribute("ticketData", ticketData);
        model.addAttribute("userId", id1);
        return "admin/a_raffles";
    }

    @GetMapping("/admin/a_ticket/{ticketId}")
    public String getTicket(@PathVariable("ticketId") Long id, Model model, Principal principal) {
        Ticket ticket = ticketService.findTicketById(id);
        Map<String, Object> ticketData = new HashMap<>();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A:" + userId;
        LocalDateTime now = LocalDateTime.now();
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

        CartDto cartDto = new CartDto();

        model.addAttribute("user", userDetails);
        model.addAttribute("userName", userName);
        model.addAttribute("ticket", ticket);
        model.addAttribute("userId", userId);
        model.addAttribute("cartDto", cartDto);
        model.addAttribute("ticketData", ticketData);// Bindungsobjekt hinzufügen

        return "admin/a_ticket";
    }

    @PostMapping("/admin/a_buy")
    public String adminKauft(@RequestParam Long ticketId, @RequestParam Long quantity, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        try {
            // Hole zunächst das Ticket, um den aktuellen Bestand (availableBeforePurchase) zu erfassen
            Ticket originalTicket = ticketService.findTicketById(ticketId);

            long availableBeforePurchase = originalTicket.getQuantity();

            // Kaufe das Ticket und update den Bestand
            Ticket updatedTicket = ticketService.purchaseTicket(ticketId, quantity);

            // Registriere die verkauften Tickets (hier beispielhaft mit Admin als userName und einem definierten Paymethod)
            soldTicketService.saveSoldTickets(originalTicket, quantity, userId,"Barzahlung", availableBeforePurchase);

            model.addAttribute("message", "Tickets erfolgreich gekauft!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        // Gib den View-Namen zurück, z. B. die Admin-Oberfläche
        return "/admin/admin";
    }

    @GetMapping("/admin/a_finished")
    public String showFinished_a(Model model, Principal principal) {
        // Userdetails und Username abrufen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "A" + id1.toString();
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
        model.addAttribute("userName", userName);
        return "admin/a_finished";
    }

}
