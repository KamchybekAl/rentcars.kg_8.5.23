package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.model.OrderDetail;
import kg.mega.rentcars_kg.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final JavaMailSender emailSender;
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com"); // можем не указывать, т.к будет идти с нашего аккаунта
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    @Override
    public void sendOrderMessage(OrderDetail orderDetail) {
        String message = "Вы забронировали "+orderDetail.getCar().getCarModel()+" на "+orderDetail.getOrderedDays() +" дней "+
                ". Итоговая сумма: "+orderDetail.getPriceWithDiscount();
        String subject = "Order details";
        sendSimpleMessage(orderDetail.getClientEmail(),subject,message);
    }
}
