package com.yukado.greenraffle.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Die von z.B. PayPal zurückgegebene Payment ID
    @Column(name = "payment_gateway_id")
    private String paymentGatewayId;

    // Referenz zur User-ID, die die Zahlung initiiert hat
    @Column(name = "user_id")
    private Long userId;

    // Betrag und Währung der Zahlung
    private BigDecimal amount;
    private String currency;

    // Verwendete Zahlungsmethode, z.B. "paypal", "kreditkarte" etc.
    @Column(name = "payment_method")
    private String paymentMethod;

    // Status der Zahlung: z.B. "CREATED", "APPROVED", "FAILED"
    private String state;

    // Zeitstempel zur Erstellung und Aktualisierung
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Standard-Konstruktor
    public Payment() { }

    // Konstruktor mit Initialisierung der meisten Felder
    public Payment(String paymentGatewayId, Long userId, BigDecimal amount, String currency,
                   String paymentMethod, String state) {
        this.paymentGatewayId = paymentGatewayId;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.state = state;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public String getPaymentGatewayId() {
        return paymentGatewayId;
    }

    public void setPaymentGatewayId(String paymentGatewayId) {
        this.paymentGatewayId = paymentGatewayId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

