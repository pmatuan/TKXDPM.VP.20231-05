package vn.hust.aims.subsystem.notification.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.hust.aims.constant.Constant;
import vn.hust.aims.subsystem.notification.NotificationSubsystem;

@Service("GMAIL")
public class GmailSender implements NotificationSubsystem {

  public JavaMailSender config(String config){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(config);

      mailSender.setHost(jsonNode.at("/mail/host").asText());
      mailSender.setPort(jsonNode.at("/mail/port").asInt());
      mailSender.setUsername(jsonNode.at("/mail/username").asText());
      mailSender.setPassword(jsonNode.at("/mail/password").asText());

      Properties props = mailSender.getJavaMailProperties();
      props.put("mail.mime.charset", jsonNode.at("/mail/properties/mail/mime/charset").asText());
      props.put("mail.smtp.auth", jsonNode.at("/mail/properties/mail/smtp/auth").asText());
      props.put("mail.smtp.starttls.enable",
          jsonNode.at("/mail/properties/mail/smtp/starttls/enable").asText());
      props.put("mail.smtp.starttls.required",
          jsonNode.at("/mail/properties/mail/smtp/starttls/required").asText());

      mailSender.setDefaultEncoding(jsonNode.at("/mail/default-encoding").asText());
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }

    return mailSender;
  }

  @Override
  public void send(String config, String destination, String title, String content) {
    JavaMailSender emailSender = this.config(config);
    try {
      MimeMessage message = createMimeMessage(destination, title, content, emailSender);
      emailSender.send(message);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  private MimeMessage createMimeMessage(String destination, String title, String content,
      JavaMailSender emailSender)
      throws MessagingException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, Constant.UTF_8_ENCODING);
    helper.setSubject(title);
    helper.setTo(destination);
    helper.setText(content, true);
    return message;
  }
}
