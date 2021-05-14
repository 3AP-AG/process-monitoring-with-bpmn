package ch.aaap.bpmn.monitoring.service;

import ch.aaap.bpmn.monitoring.MonitoringApplication;
import ch.aaap.bpmn.monitoring.domain.Booking;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.zeebe.spring.client.ZeebeClientLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StartWorkflowService {

  private final ZeebeClientLifecycle client;

  public Mono<ProcessInstanceEvent> start(Booking booking) {

    return Mono.fromCallable(
        () -> {
          return client
              .newCreateInstanceCommand()
              .bpmnProcessId(MonitoringApplication.PROCESS_ID)
              .latestVersion()
              .variables(booking)
              .send()
              .join();
        });
  }
}
