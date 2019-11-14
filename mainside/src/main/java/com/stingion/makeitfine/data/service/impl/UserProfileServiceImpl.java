/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.utils.UserProfileType;
import com.stingion.makeitfine.data.service.UserProfileService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends EntityServiceImpl<UserProfile>
    implements UserProfileService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public UserProfile findByUserProfileType(UserProfileType profileType) {
    Session session = entityManager.unwrap(Session.class);

    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<UserProfile> criteria = builder.createQuery(UserProfile.class);
    Root<UserProfile> userProfileRoot = criteria.from(UserProfile.class);
    criteria.select(userProfileRoot);
    criteria.where(builder.equal(userProfileRoot.get("type"), profileType.getUserProfileType()));
    List<UserProfile> userProfiles = entityManager.createQuery(criteria).getResultList();
    return userProfiles.isEmpty() ? null : userProfiles.get(0);
  }
}
