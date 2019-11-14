/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.UserService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends EntityServiceImpl<User> implements UserService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public User findBySSO(String sso) {
    CriteriaQuery<User> criteriaQuery = getUserCriteriaQuery(sso.toLowerCase(), "ssoId");
    List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
    return users.isEmpty() ? null : users.get(0);
  }

  @Override
  public User findByEmail(String email) {
    CriteriaQuery<User> criteriaQuery = getUserCriteriaQuery(email.toLowerCase(), "email");
    List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
    return users.isEmpty() ? null : users.get(0);
  }

  @NotNull
  private CriteriaQuery<User> getUserCriteriaQuery(String userAttribute, String email) {
    Session session = entityManager.unwrap(Session.class);
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> userRoot = criteriaQuery.from(User.class);
    criteriaQuery.select(userRoot);
    criteriaQuery.where(criteriaBuilder.equal(userRoot.get(email), userAttribute));
    return criteriaQuery;
  }
}
