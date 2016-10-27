package com.bakigoal.routes.transform;

import com.bakigoal.routes.transform.beans.OrderToCsvBean;
import com.bakigoal.routes.transform.processor.OrderToCsvProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Message Translator EIP
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class MessageTranslatorRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("file:data/inbox/transform/messageTranslator/processor?noop=true")
        .process(new OrderToCsvProcessor())
        .to("file:data/outbox/transform/messageTranslator/processor?fileName=${file:name.noext}.csv");

    from("file:data/inbox/transform/messageTranslator/bean?noop=true")
        .bean(OrderToCsvBean.class)
        .to("file:data/outbox/transform/messageTranslator/bean?fileName=${file:name.noext}.csv");

    from("file:data/inbox/transform/messageTranslator/transform?noop=true")
        .transform(new Expression() {
          public <T> T evaluate(Exchange exchange, Class<T> aClass) {
            String body = exchange.getIn().getBody(String.class);
            body = body.replaceAll("\n", "<br/>");
            return (T) ("<html><body>" + body + "</body></html>");
          }
        })
        .to("file:data/outbox/transform/messageTranslator/transform?fileName=${file:name.noext}.html");
  }
}
