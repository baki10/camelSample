package com.bakigoal.routes.baseEIP;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * WireTap Router EIP
 * sends a copy of the exchange to the queue, and the
 * original exchange continues on through the route, as if you hadn’t used a wire tap at
 * all.
 * Camel doesn’t wait for a response from the wire tap because the wire tap sets
 * the message exchange pattern ( MEP ) to InOnly
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class WireTapRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("activemq:queue:in")
        .wireTap("file:data/outbox/wiretap/wire")
        .to("file:data/outbox/wiretap/continue");
  }
}
