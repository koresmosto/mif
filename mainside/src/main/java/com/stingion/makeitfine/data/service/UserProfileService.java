/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.utils.UserProfileType;

public interface UserProfileService extends EntityService<UserProfile> {

  UserProfile findByUserProfileType(UserProfileType profileType);
}
