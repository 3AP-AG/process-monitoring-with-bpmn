package ch.aaap.bpmn.monitoring.controller;

import ch.aaap.bpmn.monitoring.domain.Booking;
import ch.aaap.bpmn.monitoring.domain.Message;
import ch.aaap.bpmn.monitoring.domain.Message.MessageType;
import ch.aaap.bpmn.monitoring.service.MessageSenderService;
import ch.aaap.bpmn.monitoring.service.StartWorkflowService;
import io.zeebe.client.api.response.PublishMessageResponse;
import io.zeebe.client.api.response.WorkflowInstanceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/guest-journey")
public class ProcessController {

  private final StartWorkflowService startWorkflowService;
  private final MessageSenderService messageSenderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<WorkflowInstanceEvent> start(@RequestBody Booking booking) {
    log.info("Start new guest journey workflow instance for booking {}", booking.getId());

    return startWorkflowService.start(booking);
  }

  @PostMapping("/{id}/{message}")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<PublishMessageResponse> send(
      @PathVariable String id, @PathVariable MessageType message) {
    log.info("Send message {} for booking {}", message, id);

    return messageSenderService.send(Message.builder().Id(id).type(message).build());
  }
}
