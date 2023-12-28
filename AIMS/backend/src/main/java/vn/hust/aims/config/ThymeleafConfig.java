package vn.hust.aims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class ThymeleafConfig {

  @Bean
  public TemplateEngine templateEngine() {
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(htmlTemplateResolver());
    return templateEngine;
  }

  @Bean
  public StringTemplateResolver htmlTemplateResolver() {
    StringTemplateResolver templateResolver = new StringTemplateResolver();
    templateResolver.setTemplateMode(TemplateMode.HTML);
    return templateResolver;
  }
}

