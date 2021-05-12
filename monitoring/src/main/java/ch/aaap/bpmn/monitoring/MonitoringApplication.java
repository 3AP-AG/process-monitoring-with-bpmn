package ch.aaap.bpmn.monitoring;

import io.zeebe.spring.client.EnableZeebeClient;
import io.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(classPathResources = "guest-journey.bpmn")
public class MonitoringApplication {

  public static final String PROCESS_ID = "guest_journey";

  public static void main(String[] args) {
    SpringApplication.run(MonitoringApplication.class, args);
  }
}
