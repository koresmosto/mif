/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.service;

import java.util.List;

public interface EntityService<T> {
    List<T> findAll();

    T findById(int id);

    T save(T entity);
}
