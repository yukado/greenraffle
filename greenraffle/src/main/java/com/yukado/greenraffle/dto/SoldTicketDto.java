package com.yukado.greenraffle.dto;

import java.time.LocalDateTime;

public class SoldTicketDto {

    private Long ticketId;
    private String cityname;
    private Long raffleId;
    private Long userId;
    private LocalDateTime sellDateTime;
    private Boolean winner;
    private double price;
    private double winamount;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String chances;
    private String paymethod;

    public SoldTicketDto() {
        super();
    }

    public SoldTicketDto(Long ticketId, String cityname, Long raffleId, Long userId, LocalDateTime sellDateTime,
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
