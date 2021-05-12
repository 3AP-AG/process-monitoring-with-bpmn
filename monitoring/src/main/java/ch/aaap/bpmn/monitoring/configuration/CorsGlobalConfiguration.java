package ch.aaap.bpmn.monitoring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry corsRegistry) {
    corsRegistry
        .addMapping("/v3/api-docs")
        .allowedOrigins("http://localhost:8090")
        .allowedMethods("GET")
        .maxAge(3600);
  }
}
