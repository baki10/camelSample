package com.bakigoal;

import com.bakigoal.routes.FileMoveRouter;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

/**
 * Created by ibakirov on 27.10.16.
 */
public class FileMoveTest extends CamelTestSupport {

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {
    return new FileMoveRouter();
  }

  @Test
  public void testMoveFile() throws Exception {
    template.sendBodyAndHeader("file:data/inbox/test", "Hello World", Exchange.FILE_NAME, "hello.txt");
    Thread.sleep(1000);
    File target = new File("data/outbox/test/hello.txt");
    assertTrue("File not moved", target.exists());
  }
}