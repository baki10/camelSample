package com.bakigoal.routes.transform;

import com.bakigoal.routes.transform.bindy.PurchaseOrder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class JacksonRoute extends SpringRouteBuilder {
  public void configure() throws Exception {


    ProducerTemplate template = getContext().createProducerTemplate();
    template.sendBody("activemq:json", null);

    from("activemq:json")
        .process(new Processor() {
          public void process(Exchange exchange) throws Exception {
            Object body = exchange.getIn().getBody();
            if (body == null) {
              exchange.getIn().setBody(createPurchase());
            }
          }
        })
        .marshal().json()
        .to("activemq:json_out");
    from("activemq:json_out")
        .to("file:data/outbox/transform/json?fileName=test.json");
  }

  private PurchaseOrder createPurchase() {
    PurchaseOrder order = new PurchaseOrder();
    order.setAmount(1);
    order.setPrice(new BigDecimal("37.95"));
    order.setName("Camel in Action");
    return order;
  }
}
