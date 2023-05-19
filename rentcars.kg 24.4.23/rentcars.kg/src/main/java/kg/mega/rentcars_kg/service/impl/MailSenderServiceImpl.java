package kg.mega.rentcars_kg.service.impl;

import kg.mega.rentcars_kg.model.OrderDetail;
import kg.mega.rentcars_kg.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final JavaMailSender emailSender;


    @Override
    public void sendSimpleMessage(String to, String subject, String text, String filePath) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            FileSystemResource file
                    = new FileSystemResource(new File(filePath));
            helper.addAttachment("CarPhoto.jpg", file);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendOrderMessage(OrderDetail orderDetail) {
        String messageDiscount = "";
        if (orderDetail.getPriceWithDiscount() != 0.0) {
            messageDiscount = "\n Итоговая сумма с учётом Вашей скидки: " + orderDetail.getPriceWithDiscount();
        }
        String message = " Вы забронировали автомобиль " + orderDetail.getCar().getCarModel() +
                "\n планируемое время аренды машины:  " + orderDetail.getOrderedDays() + " дней " +
                "\n начисленная сумма за авренду авто: " + orderDetail.getPriceBeforeDiscount() + messageDiscount;
        String subject = "Ваш заказ по аренде автомибиля";
        sendSimpleMessage(orderDetail.getClientEmail(), subject, message, orderDetail.getCar().getPhoto());
    }
}
