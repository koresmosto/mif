/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service.impl;

import com.stingion.makeitfine.data.repository.EntityRepository;
import com.stingion.makeitfine.data.service.EntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class EntityServiceImpl<T> implements EntityService<T> {

    @Resource
    private EntityRepository<T> entityRepository;

    @Override
    @Transactional
    public List<T> findAll() {
        return entityRepository.findAll();
    }

    @Override
    @Transactional
    public T findById(int id) {
        return entityRepository.findOne(id);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return entityRepository.save(entity);
    }
}
