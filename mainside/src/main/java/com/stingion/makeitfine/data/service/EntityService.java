/** Created in scope of "Make it fine" project */
package com.stingion.makeitfine.data.service;

import java.util.List;

public interface EntityService<T> {
  List<T> findAll();

  T findById(Integer id);

  T save(T entity);

  T update(T entity);

  void delete(T entity);
}
