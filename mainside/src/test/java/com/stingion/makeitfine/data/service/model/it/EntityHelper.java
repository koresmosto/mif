/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.service.model.it;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

@Slf4j
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
                    getEntityById((Integer) entity.getClass().getMethod("getId", (Class<T>[]) null).invoke(entity));
            return entity.toString().equals(dbEntity != null ? dbEntity.toString() : StringUtils.EMPTY);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

    private T getEntityById(int id) {
        // Can't avoid the expression without suppression in normal coding way (un-normal way is play with optionals)
        @SuppressWarnings("unchecked")
        T dbEntity = (T) entityManager
                .createQuery("from " + getEntityClass().getSimpleName() + " e where e.id = :inputId")
                .setParameter("inputId", id).getResultList().stream()
                .findAny()
                .orElse(null);
        return dbEntity;
    }

    protected Class<T> getEntityClass() {
        if (entityClass == null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            // Can't avoid the expression without suppression in normal coding way
            @SuppressWarnings("unchecked")
            Class<T> actualTypeArgument = (Class<T>) parameterizedType.getActualTypeArguments()[0];
            this.entityClass = actualTypeArgument;
        }
        return entityClass;
    }
}
