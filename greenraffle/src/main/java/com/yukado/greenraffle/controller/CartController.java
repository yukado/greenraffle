package com.yukado.greenraffle.controller;

import com.yukado.greenraffle.dto.CartDto;
import com.yukado.greenraffle.service.CartService;
import com.yukado.greenraffle.service.PaymentService;
import com.yukado.greenraffle.service.SoldTicketService;
import com.yukado.greenraffle.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SoldTicketService soldTicketService;


}
