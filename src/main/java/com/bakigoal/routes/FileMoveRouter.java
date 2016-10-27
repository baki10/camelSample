package com.bakigoal.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by ibakirov on 27.10.16.
 */
public class FileMoveRouter extends RouteBuilder{
  @Override
  public void configure() throws Exception {
    from("file:data/inbox/test").to("file:data/outbox/test");
  }
}
