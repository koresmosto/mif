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
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.data.service.UserSpecifiedService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSpecifiedServiceImpl implements UserSpecifiedService {

  @Autowired
  private UserService userService;

  @Override
  public List<User> specifiedMailServiceUsers(String emailHost) {
    return userService.findAll().stream()
        .filter(user -> user.getEmail().toLowerCase().endsWith(emailHost.toLowerCase()))
        .collect(Collectors.toList());
  }

  @Override
  public List<User> roleUsers(UserProfileType userProfileType) {
    return userService.findAll().stream()
        .filter(user -> user.getUserProfiles().stream()
            .anyMatch(up -> up.getType().equals(userProfileType.getUserProfileType())))
        .collect(Collectors.toList());
  }

  @Override
  public List<User> stateUsers(State userState) {
    return userService.findAll().stream().filter(user -> user.getState() == userState)
        .collect(Collectors.toList());
  }
}