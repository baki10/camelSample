package com.bakigoal.routes.usingBeans.beans;

import org.apache.camel.Body;
import org.apache.camel.language.Bean;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Created by ibakirov on 27.10.16.
 */
public class XmlOrderIdCreatorBean {

  public Document handleIncomingOrder(@Body Document xml,
                                      @Bean(ref = "guid", method = "generate") int orderId) {
    Attr attr = xml.createAttribute("orderId");
    attr.setValue("" + orderId);

    Node node = xml.getElementsByTagName("order").item(0);
    node.getAttributes().setNamedItem(attr);
    return xml;
  }
}
