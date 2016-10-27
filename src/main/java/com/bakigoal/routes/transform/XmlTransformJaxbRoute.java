package com.bakigoal.routes.transform;

import com.bakigoal.routes.transform.jaxb.PurchaseOrder;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;

/**
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class XmlTransformJaxbRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseOrder.class);
    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(jaxbContext);

    from("file:data/inbox/transform/xml/jaxb?noop=true")
        .unmarshal(jaxbDataFormat)
        .process(new Processor() {
          public void process(Exchange exchange) throws Exception {
            Message in = exchange.getIn();
            PurchaseOrder order = in.getBody(PurchaseOrder.class);
            order.setName(order.getName() + " edited");
            in.setBody(order);
          }
        })
        .marshal(jaxbDataFormat)
        .to("file:data/outbox/transform/xml/jaxb");
  }
}
