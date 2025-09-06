package com.yukado.greenraffle.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "carts", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "ticket_city", nullable = false)
    private String ticketCity;
    @Column(name = "raffle_id", nullable = false)
    private Long raffleId;
    private double price;
    private double winamount;
    private Long quantity;
    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time", nullable = false)
    private LocalDateTime endDateTime;
    private String description;

    public Cart() {
        super();
    }

    public Cart(Long userId, String userName, String ticketCity, Long raffleId,
                double price, double winamount, Long quantity, LocalDateTime startDateTime,
                  LocalDateTime endDateTime, String description) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.ticketCity = ticketCity;
        this.raffleId = raffleId;
        this.price = price;
        this.winamount = winamount;
        this.quantity = quantity;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTicketCity() {
        return ticketCity;
    }

    public void setTicketCity(String ticketCity) {
        this.ticketCity = ticketCity;
    }

    public Long getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(Long raffleId) {
        this.raffleId = raffleId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWinamount() {
        return winamount;
    }

    public void setWinamount(double winamount) {
        this.winamount = winamount;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        this.startDateTime = LocalDateTime.now();
        this.endDateTime = this.startDateTime.plusHours(48);
    }
}

