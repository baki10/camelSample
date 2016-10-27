package com.bakigoal.routes.transform;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Poll Enrich EIP
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class PollEnrichRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("file:data/inbox/transform/poll_enrich?noop=true")
        .pollEnrich("file:data/inbox/transform/poll_enrich/new?noop=true", new AggregationStrategy() {
          public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            if (newExchange == null) {
              return oldExchange;
            }
            String oldBody = oldExchange.getIn().getBody(String.class);
            String newBody = newExchange.getIn().getBody(String.class);
            String body = oldBody + "\n" + newBody;
            oldExchange.getIn().setBody(body);
            return oldExchange;
          }
        })

        .to("file:data/outbox/transform/poll_enrich");
  }
}
