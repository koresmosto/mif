/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.it;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class EntityHelper<T> {

  @PersistenceContext
  private EntityManager entityManager;

  @Setter(value = AccessLevel.PROTECTED)
  private Class<T> entityClass;

  public int getCount() {
    return ((Long)
        entityManager
            .createQuery("select count(*) from " + getEntityClass().getSimpleName())
            .getSingleResult())
        .intValue();
  }

  public boolean isExist(@NotNull T entity) {
    try {
      T dbEntity =
          getEntityById((Integer) entity.getClass().getMethod("getId", null).invoke(entity));
      return entity.toString().equals(dbEntity != null ? dbEntity.toString() : StringUtils.EMPTY);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private T getEntityById(int id) {
    return (T)
        entityManager
            .createQuery("from " + getEntityClass().getSimpleName() + " e where e.id = :inputId")
            .setParameter("inputId", id).getResultList().stream()
            .findAny();
  }

  protected Class<T> getEntityClass() {
    if (entityClass == null) {
      ParameterizedType pType = (ParameterizedType) getClass().getGenericSuperclass();
      this.entityClass = (Class<T>) pType.getActualTypeArguments()[0];
    }
    return entityClass;
  }
}
