package ch.aaap.bpmn.monitoring.service;

import ch.aaap.bpmn.monitoring.domain.Message;
import io.camunda.zeebe.client.api.response.PublishMessageResponse;
import io.camunda.zeebe.spring.client.ZeebeClientLifecycle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MessageSenderService {

  private final ZeebeClientLifecycle client;

  public Mono<PublishMessageResponse> send(Message message) {

    return Mono.fromCallable(
        () -> {
          return client
              .newPublishMessageCommand()
              .messageName(message.getType().name())
              .correlationKey(message.getId())
              .send()
              .join();
        });
  }
}
