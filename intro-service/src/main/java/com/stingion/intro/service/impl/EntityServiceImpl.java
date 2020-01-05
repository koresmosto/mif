/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.service.impl;

import com.stingion.intro.repository.EntityRepository;
import com.stingion.intro.service.EntityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class EntityServiceImpl<T> implements EntityService<T> {

  @Autowired
  private EntityRepository<T> entityRepository;

  @Override
  public List<T> findAll() {
    return entityRepository.findAll();
  }
}
