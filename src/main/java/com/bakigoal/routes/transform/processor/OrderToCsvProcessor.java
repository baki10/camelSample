package com.bakigoal.routes.transform.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/**
 * Created by ibakirov on 26.10.16.
 */
public class OrderToCsvProcessor implements Processor {
  public void process(Exchange exchange) throws Exception {
    Message in = exchange.getIn();
    String custom = in.getBody(String.class);
    String id = custom.substring(0, 9);
    String customerId = custom.substring(10, 19);
    String date = custom.substring(20, 29);
    String items = custom.substring(30);
    String[] itemIds = items.split("@");
    StringBuilder csv = new StringBuilder();
    csv.append(id.trim());
    csv.append(",").append(date.trim());
    csv.append(",").append(customerId.trim());
    for (String item : itemIds) {
      csv.append(",").append(item.trim());
    }
    String camelFileName = in.getHeader("CamelFileName", String.class);
    in.setHeader("CamelFileName",camelFileName.substring(0, camelFileName.indexOf(".")));
    in.setBody(csv.toString());
  }
}
