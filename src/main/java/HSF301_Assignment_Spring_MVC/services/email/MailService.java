package HSF301_Assignment_Spring_MVC.services.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    JavaMailSender javaMailSender;
    public void sendSimpleMessage(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Michaeldang0104@gmail.com");
        message.setTo(to);
        message.setSubject("Thank you for using our services");
        message.setText("Dear our customer, we really appreciate that you have chosen and used our service !");
        javaMailSender.send(message);
    }
}
