package com.bakigoal.routes.usingBeans.beans;

import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by ibakirov on 27.10.16.
 */
@Service
public class ConsumerBean {

  @Produce(uri = "activemq:produceTest")
  private ProducerTemplate producerTemplate;

  @Consume(uri = "activemq:consumeTest")
  public void consume(String message) {
    System.out.println("========================================");
    System.out.println("ActiveMq:consumeTest message: " + message);
    System.out.println("========================================");
    producerTemplate.sendBody("!!!Produce: " + message);
  }

}
