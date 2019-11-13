/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */
package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.UserService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UserServiceImpl extends EntityServiceImpl<User> implements UserService {

  @PersistenceContext private EntityManager entityManager;

  @Override
  @Transactional
  public User findBySSO(String sso) {
    Session session = entityManager.unwrap(Session.class);

    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<User> criteria = builder.createQuery(User.class);
    Root<User> userRoot = criteria.from(User.class);
    criteria.select(userRoot);
    criteria.where(builder.equal(userRoot.get("ssoId"), sso));
    List<User> users = entityManager.createQuery(criteria).getResultList();
    return users.isEmpty() ? null : users.get(0);
  }
}
