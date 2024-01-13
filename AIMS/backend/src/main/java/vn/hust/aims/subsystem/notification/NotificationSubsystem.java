package vn.hust.aims.subsystem.notification;

public interface NotificationSubsystem {
  void send(String config, String destination, String title, String content);
}
