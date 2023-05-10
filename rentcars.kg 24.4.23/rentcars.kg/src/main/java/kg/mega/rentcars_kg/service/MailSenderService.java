package kg.mega.rentcars_kg.service;

public interface MailSenderService {
    void sendSimpleMessage(String to, String subject, String text);
}
