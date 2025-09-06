package com.yukado.greenraffle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendRegistrationEmail(String to, String subject, String text) {
        try {
            // E-Mail-Nachricht vorbereiten
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("devpaypal157@gmail.com");  // Absender explizit setzen
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            // E-Mail versenden
            mailSender.send(message);
            System.out.println("E-Mail wurde erfolgreich an " + to + " gesendet.");
            return true;
        } catch (MailException ex) {
            System.err.println("Fehler beim Senden der E-Mail: " + ex.getMessage());
            return false;
        }
    }
}

