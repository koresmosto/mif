/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeitfine.data.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;

@ConditionalOnProperty("creation.disabled")
public interface EntityRepository<T> extends JpaRepository<T, Integer> {
}