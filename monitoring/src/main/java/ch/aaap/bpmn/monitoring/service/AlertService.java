package ch.aaap.bpmn.monitoring.service;

import ch.aaap.bpmn.monitoring.MonitoringApplication;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertService {

  @ZeebeWorker(type = MonitoringApplication.ALERT_BOOKING)
  public void alertBooking(final JobClient client, final ActivatedJob job) {

    client
        .newFailCommand(job.getKey())
        .retries(0)
        .errorMessage("Booking data incomplete")
        .send()
        .join();

    log.error("Booking data incomplete for {}", job.getVariables());
  }

  @ZeebeWorker(type = MonitoringApplication.DEFAULT_WORKER)
  public void defaultWorker(final JobClient client, final ActivatedJob job) {

    log.error("Default worker for {}", job.getVariables());
  }
}
