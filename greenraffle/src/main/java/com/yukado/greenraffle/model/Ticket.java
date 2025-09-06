package com.yukado.greenraffle.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cityname;
    private double price;
    private double winamount;
    private Long quantity;
    private Long startquantity;
    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time", nullable = false)
    private LocalDateTime endDateTime;
    private String description;
    private String winner;
    private Long winnerticket;

    public Ticket() {
        super();
    }

    public Ticket(String cityname, double price, double winamount, Long quantity,
                  Long startquantity, LocalDateTime startDateTime, LocalDateTime endDateTime, String description,
                  String winner, Long winnerticket) {
        this.cityname = cityname;
        this.price = price;
        this.winamount = winamount;
        this.quantity = quantity;
        this.startquantity = startquantity;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.winner = winner;
        this.winnerticket = winnerticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
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

    public Long getStartquantity() {
        return startquantity;
    }

    public void setStartquantity(Long startquantity) {
        this.startquantity = startquantity;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Long getWinnerticket() {
        return winnerticket;
    }

    public void setWinnerticket(Long winnerticket) {
        this.winnerticket = winnerticket;
    }

    @PrePersist
    protected void onCreate() {
        this.startDateTime = LocalDateTime.now();
        this.endDateTime = this.startDateTime.plusHours(48);
    }
}

