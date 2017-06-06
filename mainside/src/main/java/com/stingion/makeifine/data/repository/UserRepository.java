/**
 * Created under not commercial project
 */
package com.stingion.makeifine.data.repository;

import com.stingion.makeifine.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
