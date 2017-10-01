/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service;

import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.repository.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User findBySSO(String sso) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("ssoId", sso));
        return (User) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}