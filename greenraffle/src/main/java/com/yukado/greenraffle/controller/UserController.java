package com.yukado.greenraffle.controller;

import com.yukado.greenraffle.dto.CartDto;
import com.yukado.greenraffle.model.*;
import com.yukado.greenraffle.repo.CartRepo;
import com.yukado.greenraffle.repo.TicketRepo;
import com.yukado.greenraffle.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SoldTicketService soldTicketService;
    @Autowired
    TicketService ticketService;
    @Autowired
    private CartService cartService;
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    WinnerService winnerService;

    @GetMapping("/user/indexu")
    public String viewUserPage(Model model, Principal principal) {
        // GET-Methode für das Anzeigen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U" + id1.toString();
        model.addAttribute("user", userDetails);
        model.addAttribute("userName", userName);
        model.addAttribute("userId", id1);
        return "user/indexu";
    }

    @PostMapping("/user/indexu")
    public String handleUserAction(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U" + id1.toString();
        model.addAttribute("user", userDetails);
        model.addAttribute("userName", userName);
        model.addAttribute("userId", id1);
        return "/user/indexu";
    }

    @GetMapping("/user/about_u")
    public String about_u(Model model, Principal principal) {
        // GET-Methode für das Anzeigen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U" + id1.toString();
        model.addAttribute("user", userDetails);
        model.addAttribute("userName", userName);
        model.addAttribute("userId", id1);
        return "/user/about_u";  // corresponding to about.html
    }

    @GetMapping("/user/raffles_u")
    public String raffles_u(Model model, Principal principal) {
        // GET-Methode für das Anzeigen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        List<Ticket> tickets = ticketService.findAll();
        List<Map<String, Object>> ticketData = new ArrayList<>();
        String userName = "U" + id1.toString();
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

        return "/user/raffles_u";  // corresponding to raffles.html
    }

    @GetMapping("/user/myraffles")
    public String myraffles(Model model, Principal principal) {
        // GET-Methode für das Anzeigen
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long id1 = userService.findByEmail(userDetails.getUsername()).getId();
        List<SoldTicket> soldTickets_u = soldTicketService.findByUserId(id1);
        String userName = "U" + id1.toString();
        model.addAttribute("user", userDetails);
        model.addAttribute("userName", userName);
        model.addAttribute("soldTickets_u", soldTickets_u);
        model.addAttribute("userId", id1);

        return "/user/myraffles";  // corresponding to raffles.html
    }

    @GetMapping("/user/ticket_u/{ticketId}")
    public String getTicket(@PathVariable("ticketId") Long id, Model model, Principal principal) {
        Ticket ticket = ticketService.findTicketById(id);
        Map<String, Object> ticketData = new HashMap<>();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U:" + userId;
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

        return "user/ticket_u";
    }


    @PostMapping("/user/addtocart")
    public String addToCart(@RequestParam("ticketid") Long ticketid, Principal principal) {
        // 1. Ticket finden über die übergebene raffleId (Ticket-ID)
       Ticket ticket = ticketRepo.findTicketById(ticketid);
       UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = userService.findByEmail(userDetails.getUsername());
        // 2. (Optional) Hier kannst du, statt den userId aus dem Formular zu nehmen,
        //    den aktuell angemeldeten User über die Security-Context abrufen.
        Long userId = user.getId();

        // 3. Neues Cart-Entity erstellen und Daten aus dem DTO sowie das Ticket übernehmen
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setUserName(userDetails.getUsername());
        cart.setTicketCity(ticket.getCityname());
        cart.setRaffleId(ticket.getId());
        cart.setPrice(ticket.getPrice());
        cart.setWinamount(ticket.getWinamount());
        cart.setQuantity(1L);
        cart.setStartDateTime(ticket.getStartDateTime());
        cart.setEndDateTime(ticket.getEndDateTime());
        cart.setDescription(ticket.getDescription());
        // Weitere Felder setzen, falls gewünscht

        // 4. In der Datenbank in der Tabelle "carts" speichern
        cartRepo.save(cart);

        // 5. Nach dem Speichern Umleitung zur Warenkorbseite, in der alle Einträge dieses Users geladen werden.
        return "redirect:/user/cart";
    }

    @GetMapping("/user/cart")
    public String viewCartItems(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U" + userId;
        // Alle Cart-Einträge des Users abrufen
        List<Cart> carts = cartService.findByUserId(userId);
        System.out.println("Gefundene Cart-Einträge für U" + userId + ": " + carts.size());
        model.addAttribute("carts", carts);
        model.addAttribute("userName", userName);
        model.addAttribute("user", userDetails);
        model.addAttribute("userId", userId);
        return "/user/cart";  // Thymeleaf Template für die Warenkorbansicht
    }


    // Endpoint zum Löschen des Warenkorbs
    @PostMapping("/user/clear")
    public String clearCart(Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        cartService.clearCartByUserId(userId);
        return "redirect:/user/cart";
    }

    @GetMapping("/user/winnerstable_u")
    public String showWinner(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U" + userId;
        List<Winner> winner = winnerService.findAll();
        model.addAttribute("winner", winner);
        model.addAttribute("userName", userName);
        return "user/winnerstable_u";
    }

    @GetMapping("/user/finished_u")
    public String showFinished_u(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U" + userId;
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
        return "user/finished_u";
    }


    /**
     * Zeigt die Account-Seite an.
     * Hier wird angenommen, dass die User-ID als Request-Parameter mitgegeben wird.
     * In einer echten Anwendung sollte die ID über die Authentifizierung (z.B. SecurityContext) ermittelt werden.
     */
    @GetMapping("/user/account")
    public String viewAccount(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        String userName = "U" + userId;
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userName", userName);
        model.addAttribute("userId", userId);
        return "user/account"; // Thymeleaf-Template account.html
    }

    /**
     * Aktualisiert die Benutzerdaten.
     */
    @PostMapping("/user/update")
    public String updateAccount(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        user.setRole("USER");
        boolean success = userService.updateUser(user);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "account.updated");
        } else {
            redirectAttributes.addFlashAttribute("message", "Fehler beim Aktualisieren des Accounts.");
        }
        // Nach der Aktualisierung wieder zurück zur Account-Seite
        return "redirect:/user/account";
    }

    /**
     * Löscht den User-Account.
     */
    @GetMapping("/user/delete")
    public String deleteAccount(RedirectAttributes redirectAttributes, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        Long userId = userService.findByEmail(userDetails.getUsername()).getId();
        boolean success = userService.deleteUser(userId);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Account erfolgreich gelöscht.");
            // Falls der Account gelöscht wurde, wird ein Logout empfohlen
            return "redirect:/index";
        } else {
            redirectAttributes.addFlashAttribute("message", "Fehler beim Löschen des Accounts.");
            return "redirect:/logout";
        }
    }
}
