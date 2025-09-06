package com.yukado.greenraffle.dto;

import java.time.LocalDate;

public class WinnerDto {
    // Als Long – ggf. auch als Bezug auf eine User-Entity (hier vereinfachend)
    private Long userid;

    // Gewinner-Ticketnummer
    private Long winnerticket;

    // ID des Raffles, mit dem der Gewinn verknüpft ist
    private Long raffleid;

    private String ticketcity;

    private String country;

    private LocalDate date;

    private double winamount;

    private double win;

    private double price;

    private String chance;

    private boolean timeout;

    private  Long soldTickets;

    public WinnerDto() {

    }

    public WinnerDto(Long userid, Long winnerticket, Long raffleid, String ticketcity, String country, LocalDate date,
                     double winamount, double win, double price, String chance, boolean timeout, Long soldTickets) {
        super();
        this.userid = userid;
        this.winnerticket = winnerticket;
        this.raffleid = raffleid;
        this.ticketcity = ticketcity;
        this.country = country;
        this.date = date;
        this.winamount = winamount;
        this.win = win;
        this.price = price;
        this.chance = chance;
        this.timeout = timeout;
        this.soldTickets = soldTickets;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getWinnerticket() {
        return winnerticket;
    }

    public void setWinnerticket(Long winnerticket) {
        this.winnerticket = winnerticket;
    }

    public Long getRaffleid() {
        return raffleid;
    }

    public void setRaffleid(Long raffleid) {
        this.raffleid = raffleid;
    }

    public String getTicketcity() {
        return ticketcity;
    }

    public void setTicketcity(String ticketcity) {
        this.ticketcity = ticketcity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWinamount() {
        return winamount;
    }

    public void setWinamount(double winamount) {
        this.winamount = winamount;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getChance() {
        return chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public Long getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Long soldTickets) {
        this.soldTickets = soldTickets;
    }
}
