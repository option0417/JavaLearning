package tw.com.wd.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class MailSender {
    public void send() {
        Properties props = createProperties();
        Session session = createSession(props);
session.setDebug(true);
        Transport transport = null;

        try {
            transport = session.getTransport();

            transport.connect("webmail.feib.com.tw", 25, "eimservice", "mitake@86136982");
        } catch (Exception e) {
            closeConnection(transport);
        }

        try {
            if (transport.isConnected()) {
                Message message = createMessage(session);
                transport.sendMessage(message, message.getAllRecipients());
                System.err.println("Message sent.");
            } else {
                System.err.println("Transport not connected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeConnection(transport);
    }

    private Properties createProperties() {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        return props;
    }

    private Session createSession(Properties props) {
        return Session.getDefaultInstance(props);
    }

    private void closeConnection(Transport transport) {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MimeMessage createMessage(Session session) throws MessagingException, IOException {
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("eimservice@feib.com.tw", "eimservice"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress("weide_su@mitake.com.tw"));
        msg.setSubject("JustForTest_" + System.currentTimeMillis());

        // Create multipart
        MimeMultipart mp = new MimeMultipart();

        // Add Text body
        BodyPart part = new MimeBodyPart();
        part.setContent("Just for test", "text/html; charset=utf-8");
        mp.addBodyPart(part);
        msg.setContent(mp);

        return msg;
    }
}
