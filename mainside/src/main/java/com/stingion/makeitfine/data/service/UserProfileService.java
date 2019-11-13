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
  UserProfile save(UserProfile entity) throws UnsupportedOperationException;

  /**
   * Not supported method
   *
   * @param entity
   * @return
   * @throws UnsupportedOperationException
   */
  @Override
  UserProfile update(UserProfile entity) throws UnsupportedOperationException;

  /**
   * Not supported method
   *
   * @param entity
   * @throws UnsupportedOperationException
   */
  @Override
  void delete(UserProfile entity) throws UnsupportedOperationException;
}
