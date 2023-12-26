package vn.hust.aims.subsystem.email;

import vn.hust.aims.subsystem.email.dto.input.SendInput;

public interface MailSender {
  void send(SendInput input);
}
