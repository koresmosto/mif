/**
 * Created in scope of "Make it fine" project
 */
package com.stingion.makeifine.data.repository;

import com.stingion.makeifine.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
