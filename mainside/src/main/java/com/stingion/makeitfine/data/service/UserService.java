/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.User;

public interface UserService {
    User findById(int id);

    User findBySSO(String sso);

    User save(User user);
}
