package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.model.ticket.dto.CreateTicketDto;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;


@Service
@RequiredArgsConstructor
public class EmailService {


    private static final String EMAIL_ADDRESS = "<email_sender>";
    private static final String EMAIL_PASSWORD = "<password>";

    private static void send(String to, String title, String html) {
        try {
            System.out.println("SENDING EMAIL MESSAGE ...");
            Session session = createSession();
            MimeMessage mimeMessage = new MimeMessage(session);
            prepareEmailMessage(mimeMessage, to, title, html);
            Transport.send(mimeMessage);
            System.out.println("EMAIL MESSAGE HAS BEEN SENT ...");
        } catch (Exception e) {
            throw new AppServiceException("Sending an email failed " + e.getMessage());
        }
    }

    private static void prepareEmailMessage(MimeMessage mimeMessage, String to, String title, String html) {
        try {
            mimeMessage.setContent(html, "text/html; charset=utf-8");
            mimeMessage.setFrom(new InternetAddress(EMAIL_ADDRESS));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mimeMessage.setSubject(title);
        } catch (Exception e) {
            throw new AppServiceException("prepareEmailMessage exception = " + e.getMessage());
        }
    }

    private static Session createSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_ADDRESS, EMAIL_PASSWORD);
            }
        });
    }
    public void orderToMail(String email, List<CreateTicketDto> createTicketDto, BigDecimal totalPrice) {
        if (Objects.isNull(email)) {
            throw new AppServiceException("User email is invalid " + email);
        }

        var message = createTicketDto
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));

        send(email,"Cinema Order", ticketInfoToHtml(message + ": Total price with discounts: " + totalPrice));

    }

    private String ticketInfoToHtml(String html) {
        return body(h1(html)).render();
    }


}
