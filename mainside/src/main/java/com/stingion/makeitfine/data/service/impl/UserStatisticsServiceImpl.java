/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.UserStatisticsService;
import java.util.List;

public class UserStatisticsServiceImpl implements UserStatisticsService {

  @Override
  public List<User> specifiedMailServiceUsers(String emailHost) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<User> roleUsers(UserProfileType userProfileType) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<User> stateUsers(State userState) {
    throw new UnsupportedOperationException();
  }
}
