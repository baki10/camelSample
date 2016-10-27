package com.bakigoal.routes.baseEIP;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Content Based Router
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class ContentBasedRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("activemq:queue:in")
        .choice()
        .when(body().endsWith("0")).to("file:data/outbox/cbr/0")
        .when(body().endsWith("7")).to("file:data/outbox/cbr/not_continue").stop()
        .otherwise().to("file:data/outbox/cbr/others")
        .end()
        .to("file:data/outbox/cbr/continue");
  }
}
