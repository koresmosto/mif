/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.UserProfile;

import java.util.List;

public interface UserProfileService extends EntityService<UserProfile> {

    List<UserProfile> findAll();

    UserProfile findById(int id);
}
