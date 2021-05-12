package ch.aaap.bpmn.monitoring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
  private String Id;
  private MessageType type;

  public static enum MessageType {
    BOOKING,
    ONBOARDING,
    CHECKIN,
    PROPERTY_ACCESS,
    CHECKOUT;
  }
}
