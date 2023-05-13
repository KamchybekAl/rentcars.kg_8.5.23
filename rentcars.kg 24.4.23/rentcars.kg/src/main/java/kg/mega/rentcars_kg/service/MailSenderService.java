package kg.mega.rentcars_kg.service;

import kg.mega.rentcars_kg.model.OrderDetail;

public interface MailSenderService {
    void sendSimpleMessage(String to, String subject, String text,String filePath);
    void sendOrderMessage(OrderDetail orderDetail);

}
