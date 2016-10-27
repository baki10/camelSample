package com.bakigoal.routes.baseEIP;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by ibakirov on 26.10.16.
 */
@Component
public class SimpleRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("timer:foo?period=1s")
        .transform().simple("Heartbeat ${date:now:HH:mm:ss}")
        .to("activemq:queue:in");

    from("activemq:queue:in")
        .process(new Processor() {
          public void process(Exchange exchange) throws Exception {
            System.out.println("We just downloaded: " + exchange.getIn().getBody());
          }
        })
        .transform().simple("Edited ${body}")
        .to("activemq:queue:out");
    from("activemq:queue:in")
        .log(LoggingLevel.INFO, "Print ${body}")
        .to("stream:out");
  }
}
