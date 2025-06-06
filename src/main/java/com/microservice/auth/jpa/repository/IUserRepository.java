package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long > {
   Optional<UserEntity> findByEmail(String emailFF);
}
