package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComponentRepository extends JpaRepository<ComponentEntity, Long> {
}
