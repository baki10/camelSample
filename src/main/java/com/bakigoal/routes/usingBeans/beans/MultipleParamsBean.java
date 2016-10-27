package com.bakigoal.routes.usingBeans.beans;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;

/**
 * Created by ibakirov on 27.10.16.
 */
public class MultipleParamsBean {

  public String hello(@Header("customerId") Integer customerId, @Body String body, Exchange exchange) {
    return "---Multiple: exchange.in.body = " + exchange.getIn().getBody()
        + ",\n---header.customerId = " + customerId + ", body = " + body;
  }

}
