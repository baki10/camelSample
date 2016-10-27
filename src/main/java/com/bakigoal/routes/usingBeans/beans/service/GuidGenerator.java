package com.bakigoal.routes.usingBeans.beans.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by ibakirov on 27.10.16.
 */
@Service("guid")
public class GuidGenerator {

  public int generate() {
    Random ran = new Random();
    return ran.nextInt(10000000);
  }
}
