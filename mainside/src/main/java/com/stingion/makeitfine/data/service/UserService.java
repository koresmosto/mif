/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.User;

import java.util.List;

public interface UserService {
    User findById(int id);

    User findBySSO(String sso);

    User save(User user);

    List<User> findAll();
}
