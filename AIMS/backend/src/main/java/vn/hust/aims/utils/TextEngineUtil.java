package vn.hust.aims.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import vn.hust.aims.entity.email.Param;
import vn.hust.aims.entity.email.Template;

@Component
@Slf4j
public class TextEngineUtil {

  private final TemplateEngine templateEngine;

  @Autowired
  public TextEngineUtil(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  public String renderTemplate(String template, List<Param> params) {
    Context context = createContext(params);
    return templateEngine.process(template, context);
  }

  public Context createContext(List<Param> params) {
    Context context = new Context();
    for (Param param : params) {
      String key = param.getKey();
      String value = param.getValue();
      context.setVariable(key, value);
    }
    return context;
  }

  public Boolean areRequiredParametersProvided(Template template, List<Param> params) {
    String requiredParametersJson = template.getRequiredParameters();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      List<String> requiredParameters = objectMapper.readValue(requiredParametersJson,
          new TypeReference<List<String>>() {
          });

      for (String requiredParamKey : requiredParameters) {
        boolean paramFound = false;
        for (Param param : params) {
          if (param.getKey().equals(requiredParamKey)) {
            paramFound = true;
            break;
          }
        }

        if (!paramFound) {
          return false;
        }
      }

      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }
}
