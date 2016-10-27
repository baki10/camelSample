package com.bakigoal.routes.usingBeans.beans;

import org.apache.camel.language.Bean;
import org.apache.camel.language.XPath;

/**
 * Created by ibakirov on 27.10.16.
 */
public class AnnotationsBean {

  public String updateStatus(@XPath("/order/@customerId") Integer customerId,
                             @XPath("/order/status/text()") String status,
                             @Bean(ref = "guid", method = "generate") int orderId) {
    return "+++id: " + customerId + ", status: " + status + ", generated orderId: " + orderId;
  }

}
