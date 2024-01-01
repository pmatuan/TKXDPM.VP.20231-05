package vn.hust.aims.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import vn.hust.aims.entity.email.Sender;
import vn.hust.aims.entity.email.Template;
import vn.hust.aims.repository.email.SenderRepository;
import vn.hust.aims.repository.email.TemplateRepository;
import vn.hust.aims.service.MailService;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.subsystem.notification.NotificationSubsystem;
import vn.hust.aims.utils.TextEngineUtil;


@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final ApplicationContext applicationContext;
  private final SenderRepository senderRepository;
  private final TemplateRepository templateRepository;
  private final TextEngineUtil textEngineUtil;
  private NotificationSubsystem notificationSubsystem;

  public void send(SendEmailInput input) {

    if (!input.getStatus()) {
      return;
    }

    notificationSubsystem = applicationContext.getBean("GMAIL", NotificationSubsystem.class);
    Sender sender = senderRepository.findByProvider("GMAIL");
    Template template = templateRepository.findByTitle(input.getTemplateName());

    String renderedTitle = textEngineUtil.renderTemplate(template.getTitle(), input.getParams());
    String renderedContent = textEngineUtil.renderTemplate(template.getContent(), input.getParams());
    String destination = input.getDestination();

    notificationSubsystem.send(sender.getConfig(), destination, renderedTitle, renderedContent);

  }

}
