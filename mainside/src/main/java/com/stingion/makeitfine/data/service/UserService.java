/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.User;

import java.util.List;

public interface UserService extends EntityService<User> {

    List<User> findAll();

    User findById(int id);

    User findBySSO(String sso);

    User save(User user);

    User update(User user);

    void delete(User user);
}
