package ch.aaap.bpmn.monitoring.service;

import ch.aaap.bpmn.monitoring.MonitoringApplication;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.spring.client.annotation.ZeebeWorker;
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
        .errorMessage("Booking notification not sent")
        .send()
        .join();

    log.error("Booking notification not sent for {}", job.getVariables());
  }

  @ZeebeWorker(type = MonitoringApplication.DEFAULT_WORKER)
  public void defaultWorker(final JobClient client, final ActivatedJob job) {

    log.error("Default worker for {}", job.getVariables());
  }
}
