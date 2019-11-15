/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class EmailServiceTest extends ServiceTestProvision {

  @Test
  void ssoIdEmails() {
    Map<String, String> expected = new HashMap<>() {{
      put("user1", "user1@any.xxxmail");
      put("user2", "user2@any.xyz");
      put("user3", "user3@any.xxxmail");
    }};

    Map<String, String> actual = emailService.ssoIdEmails();
    assertEquals(expected, actual);
  }
}