/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("integration_test")
@Transactional
class UserSpecifiedServiceIT {

  @Autowired
  private UserSpecifiedService userSpecifiedService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserProfileService userProfileService;

  @Test
  void specifiedMailServiceUsers() {
    String emailServerName = "xyz.com";

    List<User> usersExpected = Lists.newArrayList(
        userService.findById(1),
        userService.findById(2),
        userService.findById(3),
        userService.findById(4),
        userService.findById(5)
    );

    List<User> usersActual = userSpecifiedService.specifiedMailServiceUsers(emailServerName);
    assertEquals(usersExpected, usersActual);
  }

  @Test
  void roleUsers() {
    UserProfileType userProfileType = UserProfileType.DBA;

    List<User> usersExpected = Lists.newArrayList(
        userService.findById(4),
        userService.findById(5),
        userService.findById(6)
    );

    List<User> usersActual = userSpecifiedService.roleUsers(userProfileType);
    assertEquals(usersExpected, usersActual);
  }

  @Test
  void stateUsers() {
    State userState = State.ACTIVE;

    List<User> usersExpected = Lists.newArrayList(
        userService.findById(1),
        userService.findById(2),
        userService.findById(6),
        userService.findById(7),
        userService.findById(8)
    );

    List<User> usersActual = userSpecifiedService.stateUsers(userState);
    assertEquals(usersExpected, usersActual);
  }

  @Test
  void isAdminEmail() {
    assertFalse(userSpecifiedService.isActiveAdminEmail("some word"));
    assertFalse(userSpecifiedService.isActiveAdminEmail("any_email@xyz.com"));
    assertFalse(userSpecifiedService.isActiveAdminEmail("bill@xyz.com"));
    assertFalse(userSpecifiedService.isActiveAdminEmail("BILL@XYZ.COM"));
    assertFalse(userSpecifiedService.isActiveAdminEmail("samy@xyz.com"));
    assertFalse(userSpecifiedService.isActiveAdminEmail("SAMY@XYZ.COM"));
    assertFalse(userSpecifiedService.isActiveAdminEmail("kenny@xyz.com"));
    assertFalse(userSpecifiedService.isActiveAdminEmail("nicloe@xyz.com"));

    assertTrue(userSpecifiedService.isActiveAdminEmail("admin@xxx.xxx"));
    assertTrue(userSpecifiedService.isActiveAdminEmail("ADMIN@xxx.xxx"));
  }
}