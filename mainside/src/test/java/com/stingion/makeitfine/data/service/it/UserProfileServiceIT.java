/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.it;

import static org.assertj.core.api.Assertions.assertThat;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProfileServiceIT extends EntityServiceITAncestor<UserProfile> {

  @Autowired
  private UserService userService;

  @DisplayName("Delete entity from storage")
  @Test
  public void deleteTest() {
    removeUserToUserProfileRelation();

    int beforeDelete = eH.getCount();
    UserProfile entity = entityService.findById(entityTestData.getId());
    entityService.delete(entity);
    int afterDelete = eH.getCount();

    assertThat(beforeDelete == afterDelete + 1);
    assertThat(!eH.isExist(entity));
  }

  private void removeUserToUserProfileRelation() {
    userService.delete(userService.findById(3));
    userService.delete(userService.findById(5));
    userService.delete(userService.findById(6));
    userService.delete(userService.findById(8));
  }
}
