/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.UserProfile;

public interface UserProfileService extends EntityService<UserProfile> {

  /**
   * Not supported method
   *
   * @param entity
   * @return
   * @throws UnsupportedOperationException
   */
  @Override
  UserProfile save(UserProfile entity);

  /**
   * Not supported method
   *
   * @param entity
   * @return
   * @throws UnsupportedOperationException
   */
  @Override
  UserProfile update(UserProfile entity);

  /**
   * Not supported method
   *
   * @param entity
   * @throws UnsupportedOperationException
   */
  @Override
  void delete(UserProfile entity);
}
