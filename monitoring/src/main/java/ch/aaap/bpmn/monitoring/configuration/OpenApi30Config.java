package ch.aaap.bpmn.monitoring.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApi30Config {

  @Bean
  public OpenAPI openAPI() {

    return new OpenAPI().info(new Info().title("Process monitoring with BPMN"));
  }
}
