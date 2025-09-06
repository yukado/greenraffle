package com.yukado.greenraffle.service;


/**
 * Schnittstelle für den synchronen E-Mail-Versand.
 */
public interface EmailService {

    /**
     * Sendet synchron eine Registrierungsbestätigung per E-Mail.
     *
     * @param to      die Empfängeradresse
     * @param subject der Betreff der E-Mail
     * @param text    der Inhalt der E-Mail
     * @return true, wenn die E-Mail erfolgreich versendet wurde, andernfalls false.
     */
    boolean sendRegistrationEmail(String to, String subject, String text);
}

