package vn.hust.aims.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import vn.hust.aims.entity.email.Sender;
import vn.hust.aims.entity.email.Template;
import vn.hust.aims.repository.email.SenderRepository;
import vn.hust.aims.repository.email.TemplateRepository;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.subsystem.email.MailSender;
import vn.hust.aims.utils.TextEngineUtil;


@Service
@RequiredArgsConstructor
public class MailService {

  private final ApplicationContext applicationContext;
  private final OrderService orderService;
  private final SenderRepository senderRepository;
  private final TemplateRepository templateRepository;
  private final TextEngineUtil textEngineUtil;
  private MailSender mailSender;

  public void send(SendEmailInput input) {

    if (!input.getStatus()) {
      return;
    }

    mailSender = applicationContext.getBean("GMAIL", MailSender.class);
    Sender sender = senderRepository.findByProvider("GMAIL");
    Template template = templateRepository.findByTitle(input.getTemplateName());

    String renderedTitle = textEngineUtil.renderTemplate(template.getTitle(), input.getParams());
    String renderedContent = textEngineUtil.renderTemplate(template.getContent(), input.getParams());
    String destination = orderService.getCustomerEmailFromOrder(input.getOrderId());

    mailSender.send(sender.getConfig(), destination, renderedTitle, renderedContent);

  }

}
