package vn.hust.aims.subsystem.email;

public interface MailSender {
  void send(String config, String destination, String title, String content);
}
