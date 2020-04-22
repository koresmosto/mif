/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model;

import com.stingion.makeitfine.data.model.user.User;

public interface UserService extends EntityService<User> {

    User findBySSO(String sso);

    User findByEmail(String sso);

    /**
     * Check whether user with {@code ssoIdOrEmail} as ssoId exists.
     * If there is no such ssoId, it tries to find user with {@code ssoIdOrEmail} as
     * email
     *
     * @param ssoIdOrEmail param means ssoId or email
     * @return checking
     */
    boolean isSsoIdOrEmailUserExists(String ssoIdOrEmail);
}
