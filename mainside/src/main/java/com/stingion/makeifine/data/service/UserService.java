/**
 * Created under not commercial project
 */
package com.stingion.makeifine.data.service;

import com.stingion.makeifine.data.model.User;

public interface UserService {
    User findById(int id);

    User findBySSO(String sso);

    User save(User user);
}
