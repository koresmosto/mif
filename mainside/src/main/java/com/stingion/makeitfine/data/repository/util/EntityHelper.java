package com.stingion.makeitfine.data.repository.util;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public class EntityHelper<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public int getCount() {
        return ((Long) entityManager.createQuery("select count(*) from " + getEntityClass().getSimpleName())
                .getSingleResult()).intValue();
    }

    @Setter(value = AccessLevel.PROTECTED)
    private Class<T> entityClass;

    protected Class<T> getEntityClass() {
        if (entityClass == null) {
            ParameterizedType pType = (ParameterizedType) getClass().getGenericSuperclass();
            this.entityClass = (Class<T>) pType.getActualTypeArguments()[0];
        }
        return entityClass;
    }
}
