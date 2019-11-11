/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.user.User;

public interface UserService extends EntityService<User> {
    User findBySSO(String sso);
}
