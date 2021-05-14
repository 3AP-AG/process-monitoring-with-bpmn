package ch.aaap.bpmn.monitoring;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(classPathResources = "guest-journey.bpmn")
public class MonitoringApplication {

  public static final String PROCESS_ID = "guest_journey";
  public static final String ALERT_BOOKING = "alert-booking";
  public static final String DEFAULT_WORKER = "default-worker";

  public static void main(String[] args) {
    SpringApplication.run(MonitoringApplication.class, args);
  }
}
