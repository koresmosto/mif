/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.makeitfine.data.service;

import java.util.List;

public interface EntityService<T> {
  List<T> findAll();

  T findById(Integer id);

  T save(T entity);

  T update(T entity);

  void delete(T entity);
}
