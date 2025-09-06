package com.yukado.greenraffle.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "soldtickets", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
    public class SoldTicket {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private  Long id;
        @Column(name = "ticket_id")
        private Long ticketId;
        private String cityname;
        @Column(name = "raffle_id")
        private Long raffleId;
        @Column(name = "user_id")
        private Long userId;
        @Column(name = "sell_date_time")
        private LocalDateTime sellDateTime;
        private Boolean winner;
        private double price;
        private double winamount;
        @Column(name = "start_date_time")
        private LocalDateTime startDateTime;
        @Column(name = "end_date_time")
        private LocalDateTime endDateTime;
        private String chances;
        private String paymethod;

        public SoldTicket() {
            super();
        }

        public SoldTicket(Long ticketId, String cityname, Long raffleId, Long userId, LocalDateTime sellDateTime,
                          Boolean winner, double price, double winamount,
                          LocalDateTime startDateTime, LocalDateTime endDateTime, String chances, String paymethod) {
            this.ticketId = ticketId;
            this.cityname = cityname;
            this.raffleId = raffleId;
            this.userId = userId;
            this.sellDateTime = sellDateTime;
            this.winner = winner;
            this.price = price;
            this.winamount = winamount;
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.chances = chances;
            this.paymethod = paymethod;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Long getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(Long raffleId) {
        this.raffleId = raffleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getSellDateTime() {
        return sellDateTime;
    }

    public void setSellDateTime(LocalDateTime sellDateTime) {
        this.sellDateTime = sellDateTime;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
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

    public String getChances() {
        return chances;
    }

    public void setChances(String chances) {
        this.chances = chances;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }
}
