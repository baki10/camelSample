package com.bakigoal.routes.transform;

import com.bakigoal.routes.transform.jaxb.PurchaseOrder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;

/**
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class VelocityTemplateRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseOrder.class);
    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(jaxbContext);

    from("file:data/inbox/transform/velocity?noop=true")
        .unmarshal(jaxbDataFormat)
        .to("velocity://templates/email.vm")
        .to("file:data/outbox/transform/velocity?fileName=velocity.txt");
  }
}
