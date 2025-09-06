# 🌱 GreenRaffle – Nachhaltig gewinnen, sinnvoll spenden

**GreenRaffle** ist eine innovative Verlosungsplattform, die hohe Gewinnchancen mit sozialem und ökologischem Engagement verbindet. Nutzer können Lose kaufen, an spannenden Ziehungen teilnehmen und freiwillig einen Teil ihres Gewinns für Umweltprojekte spenden.

---

## 🚀 Features

- **Registrierung & Login**  
  Sichere Benutzerverwaltung mit Spring Security

- **Loskauf via PayPal oder Kreditkarte**  
  Bequeme Zahlungsabwicklung für registrierte Mitglieder

- **Hohe Gewinnchancen**  
  Die gesamten Loseinnahmen fließen in einen Hauptgewinn  
  *Beispiel*: 100 Lose à 100 € = 10.000 € Gewinn

- **Countdown-basierte Ziehungen**  
  Jede Verlosung hat einen eigenen Countdown. Die Ziehung erfolgt automatisch bei Ablauf oder wenn alle Lose verkauft sind

- **Automatische Gewinnerbenachrichtigung**  
  Gewinner erhalten eine E-Mail mit ihrem Gewinn

- **Freiwillige Spendenmöglichkeit**  
  Gewinner können einen Teil ihres Gewinns für Umweltprojekte und die Plattform spenden

- **Gewinnertabelle**  
  Übersicht aller abgeschlossenen Ziehungen und Gewinner

- **Profilseite für Nutzer**  
  Persönliche Daten bearbeiten und eigene Teilnahme im Blick behalten

- **Admin-Oberfläche**  
  Neue Verlosungen erstellen, Nutzer verwalten und Daten bearbeiten

- **About-Seite**  
  Hintergrundinfos zur Mission von GreenRaffle

## 🌍 Mehrsprachigkeit

Green Raffle unterstützt mehrere Sprachen durch die Verwendung von `message.properties`-Dateien.  
Die Benutzeroberfläche passt sich dynamisch an die gewählte Sprache an (Deutsch, Englisch, Französisch etc.).

Beispiel:
- `messages_de.properties`
- `messages_en.properties`
- `messages_fr.properties`

Dies ermöglicht eine barrierefreie und internationale Nutzung der Plattform.

---

## 🛠️ Verwendete Technologien

| Technologie        | Zweck                            |
|--------------------|----------------------------------|
| Spring Security    | Authentifizierung & Autorisierung |
| Spring Data JPA    | Datenbankzugriff (MySQL)         |
| Spring SMTP        | E-Mail-Versand                   |
| MySQL              | Persistente Datenspeicherung     |
| HTML/CSS/JavaScript| Benutzeroberfläche               |

---

🌍 Mission
GreenRaffle möchte nicht nur Gewinne ermöglichen, sondern auch einen Beitrag zur Nachhaltigkeit leisten. Durch freiwillige Spenden der Gewinner werden Umweltprojekte unterstützt und die Plattform weiterentwickelt.

📄 Lizenz
Dieses Projekt steht unter der MIT-Lizenz. Beiträge sind willkommen!

## 📦 Installation

```bash
# Projekt klonen
git clone https://github.com/yukado/greenraffle.git
cd greenraffle

# Backend starten (Spring Boot)
./mvnw spring-boot:run

# Frontend öffnen im Browser
http://localhost:8080
Hinweis: Datenbankverbindung und SMTP müssen ggf. in der application.properties konfiguriert werden.
