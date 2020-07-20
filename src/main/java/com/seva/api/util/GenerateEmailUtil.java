package com.seva.api.util;

import com.seva.api.entity.SevaUser;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class GenerateEmailUtil {

    public void generateRegistrationVerificationMail(SevaUser sevaUser, String JWTToken) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("end.gaming005@gmail.com", "HevletPackard@123");
                    }
                });
        //compose message
        try {
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sevaUser.getSuEmail()));
            message.setSubject("Verify Your Account - Seva Mandal");
            message.setContent("<div>\n" +
                    "  <h2>Hi " + sevaUser.getSuName() + ",</h2>\n" +
                    "  <br />\n" +
                    "  <p>Please click on below link within 3 days to verify your account.</p>\n" +
                    "<a href=\"http://localhost:4200/verifyAccount/" + JWTToken + "\">verify</a>\n" +
                    "</div>", "text/html");
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}

