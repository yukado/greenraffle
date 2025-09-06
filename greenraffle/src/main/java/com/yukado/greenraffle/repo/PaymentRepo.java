package com.yukado.greenraffle.repo;

import com.yukado.greenraffle.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    // Hier können weitere, spezifische Abfragemethoden definiert werden,
    // z.B. nach Zahlungsstatus oder Benutzer filtern.
}

