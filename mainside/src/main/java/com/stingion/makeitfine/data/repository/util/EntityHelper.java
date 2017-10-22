package com.stingion.makeitfine.data.repository.util;

import lombok.AccessLevel;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

public class EntityHelper<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public int getCount() {
        return ((Long) entityManager.createQuery("select count(*) from " + getEntityClass().getSimpleName())
                .getSingleResult()).intValue();
    }

    public boolean isExist(T entity) {
        try {
            T dbUser = (T) Optional.of(entityManager.createQuery("from " + getEntityClass().getSimpleName()
                    + " e where e.id = :inputId")
                    .setParameter("inputId", entity.getClass().getMethod("getId", null).invoke(entity))
                    .getResultList().stream().findAny().orElse(StringUtils.EMPTY));
            return dbUser.toString().equals(entity.toString());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
