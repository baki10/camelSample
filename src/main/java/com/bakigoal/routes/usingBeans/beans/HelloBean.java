package com.bakigoal.routes.usingBeans.beans;

import org.apache.camel.Handler;

/**
 * Created by ibakirov on 27.10.16.
 */
public class HelloBean {

  public String hello(String name) {
    return "Input message in helloBean.hello: " + name;
  }

  public String empty() {
    return "Input message is empty";
  }

  @Handler
  public String hello2(String name) {
    return "Input message in helloBean.hello2: " + name;
  }

}
