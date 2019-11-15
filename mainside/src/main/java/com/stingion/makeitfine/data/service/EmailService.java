/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service;

public interface EmailService {

  /**
   * Check whether email belongs to any active admin
   *
   * @param email given email address
   * @return check result
   */
  boolean isActiveAdminEmail(String email);
}
