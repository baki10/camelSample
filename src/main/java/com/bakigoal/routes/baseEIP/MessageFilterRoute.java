package com.bakigoal.routes.baseEIP;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Message Filter Router EIP
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class MessageFilterRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("activemq:queue:in")
        .filter(body().endsWith("0"))
        .to("file:data/outbox/message_filter");
  }
}
