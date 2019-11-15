/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.data.service.impl.InfoServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InfoServiceTest {

  @Mock
  private UserService userService;

  @InjectMocks
  @Spy
  private InfoServiceImpl infoService;

  @BeforeEach
  void beforeEach() {

    User user1 = new User();
    user1.setSsoId("user1");
    User user2 = new User();
    user2.setSsoId("user2");
    User user3 = new User();
    user3.setSsoId("user3");

    UserProfile adminUp = new UserProfile();
    adminUp.setId(1);
    adminUp.setType(UserProfileType.ADMIN.getUserProfileType());

    UserProfile userUp = new UserProfile();
    userUp.setId(2);
    userUp.setType(UserProfileType.USER.getUserProfileType());

    UserProfile dbaUp = new UserProfile();
    dbaUp.setId(3);
    dbaUp.setType(UserProfileType.DBA.getUserProfileType());

    user1.setUserProfiles(Sets.newHashSet(adminUp, dbaUp, userUp));
    user3.setUserProfiles(Sets.newHashSet(adminUp, dbaUp, userUp));

    when(userService.findAll()).thenReturn(Lists.newArrayList(user1, user2, user3));
  }

  @Test
  void usersAndItsRoles() {
    List<String> expected = Lists
        .newArrayList("user1:{USER,ADMIN,DBA}", "user2:{}", "user3:{USER,ADMIN,DBA}");
    List<String> actual = infoService.usersAndItsRoles();
    assertEquals(expected, actual);
  }
}