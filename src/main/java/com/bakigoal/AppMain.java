package com.bakigoal;

import org.apache.camel.spring.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ibakirov on 26.10.16.
 */
public class AppMain {
  public static void main(String[] args) throws Exception {
    Main main = new Main();
    main.setApplicationContext(new ClassPathXmlApplicationContext("camelContext.xml"));
    main.enableHangupSupport();
    main.start();
  }
}
