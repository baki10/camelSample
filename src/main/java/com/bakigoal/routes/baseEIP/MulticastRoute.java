package com.bakigoal.routes.baseEIP;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Multicast Router EIP
 * <p>
 * Created by ibakirov on 26.10.16.
 */
@Component
public class MulticastRoute extends SpringRouteBuilder {
  public void configure() throws Exception {
    from("activemq:queue:in")
        .multicast()
        .to("file:data/outbox/multicast/1", "file:data/outbox/multicast/2");

    //parallel
//    //A default thread pool size of 10 is used
//    ExecutorService executor = Executors.newFixedThreadPool(16);
//    from("activemq:queue:in")
//        .multicast().stopOnException()
//        .parallelProcessing().executorService(executor)
//        .to("file:data/outbox/multicastParallel/1", "file:data/outbox/multicastParallel/2");
  }
}
