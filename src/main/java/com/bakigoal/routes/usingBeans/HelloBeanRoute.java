package com.bakigoal.routes.usingBeans;

import com.bakigoal.routes.usingBeans.beans.AnnotationsBean;
import com.bakigoal.routes.usingBeans.beans.XmlOrderIdCreatorBean;
import com.bakigoal.routes.usingBeans.beans.HelloBean;
import com.bakigoal.routes.usingBeans.beans.MultipleParamsBean;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class HelloBeanRoute extends SpringRouteBuilder {
  public void configure() throws Exception {

//    from("activemq:queue:in").bean(HelloBean.class, "hello").to("stream:out");
    from("activemq:queue:in").bean(HelloBean.class).to("stream:out");

    from("activemq:queue:in").process(new Processor() {
      public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader("customerId",123);
      }
    }).bean(MultipleParamsBean.class).to("stream:out");

    from("file:data/inbox/beans?noop=true").to("activemq:xmlOrderBeanExample");
    from("activemq:xmlOrderBeanExample").bean(AnnotationsBean.class).to("stream:out");
    from("activemq:xmlOrderBeanExample").bean(XmlOrderIdCreatorBean.class).to("file:data/outbox/beans");

    from("activemq:queue:in").to("activemq:consumeTest");
    from("activemq:consumeTest").to("bean:consumerBean");
    from("activemq:produceTest").to("stream:out");
  }
}
