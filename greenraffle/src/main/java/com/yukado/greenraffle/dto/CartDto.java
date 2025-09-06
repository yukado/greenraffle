package com.yukado.greenraffle.dto;

import java.time.LocalDateTime;

public class CartDto {
    private Long userId;
    private String userName;
    private String ticketCity;
    private Long raffleId;
    private double price;
    private double winamount;
    private Long quantity;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public CartDto() {

    }

    public CartDto(Long userId, String userName, String ticketCity, Long raffleId,
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
}
