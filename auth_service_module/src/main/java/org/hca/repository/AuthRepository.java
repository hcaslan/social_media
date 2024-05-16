package org.hca.repository;

import org.hca.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {
    boolean existsByUsername(String username);

    Optional<Auth> findOptionalByEmailAndPassword(String email, String password);
}
