package com.bakigoal.routes.transform;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class XmlTransformXstreamRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("file:data/inbox/transform/xml/xstream?noop=true")
        .marshal().xstream()
        .to("activemq:xml_in");

    from("activemq:xml_in")
        .unmarshal().xstream()
        .to("file:data/outbox/transform/xml/xstream");
  }
}
