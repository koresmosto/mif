/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.user.User;

public interface UserService extends EntityService<User> {

  User findBySSO(String sso);

  User findByEmail(String sso);
}
