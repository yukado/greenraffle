package com.yukado.greenraffle.service;

import com.yukado.greenraffle.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean processPayment(String paymentMethod, List<Cart> cart) {
        // Beispielhafter Ablauf:
        // - Bei "accountBalance": Überprüfe, ob der User genug Guthaben hat und ziehe den Betrag ab.
        // - Bei anderen Methoden: Integration mit dem jeweiligen Zahlungsanbieter.
        // Für dieses Beispiel nehmen wir an, dass die Zahlung immer erfolgreich ist.
        return true;
    }
}