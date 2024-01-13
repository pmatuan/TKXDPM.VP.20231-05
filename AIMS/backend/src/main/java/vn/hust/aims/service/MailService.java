package vn.hust.aims.service;

import vn.hust.aims.service.dto.input.email.SendEmailInput;

public interface MailService {

  // Send email based on input parameters
  void send(SendEmailInput input);
}
