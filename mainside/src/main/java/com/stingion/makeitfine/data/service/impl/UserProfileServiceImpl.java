/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends EntityServiceImpl<UserProfile> implements UserProfileService {
}
