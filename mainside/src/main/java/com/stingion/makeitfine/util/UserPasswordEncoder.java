/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.util;

import com.stingion.makeitfine.data.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordEncoder {

  @Autowired(required = false)
  private PasswordEncoder encoder;

  /**
   * Encode password of the user if encoder present
   *
   * @param user
   * @return
   */
  public void encodePassword(User user) {
    if (encoder != null) {
      user.setPassword(encoder.encode(user.getPassword()));
    }
  }
}
