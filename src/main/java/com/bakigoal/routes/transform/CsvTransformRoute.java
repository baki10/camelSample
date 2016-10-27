package com.bakigoal.routes.transform;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class CsvTransformRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("file:data/inbox/transform/csv?noop=true")
        .unmarshal().csv()
        .split(body())
        .process(new Processor() {
          public void process(Exchange exchange) throws Exception {
            System.out.println("Csv row :" + exchange.getIn().getBody());
          }
        })
        .to("activemq:csv");
  }
}
