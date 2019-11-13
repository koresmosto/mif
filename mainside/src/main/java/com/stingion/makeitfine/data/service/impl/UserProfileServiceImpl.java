package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends EntityServiceImpl<UserProfile>
    implements UserProfileService {

  @Override
  public UserProfile save(UserProfile entity) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public UserProfile update(UserProfile entity) throws UnsupportedOperationException {
    return super.update(entity);
  }

  @Override
  public void delete(UserProfile entity) throws UnsupportedOperationException {
    super.delete(entity);
  }
}
