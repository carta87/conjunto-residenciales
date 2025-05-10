package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.SpaceCommunalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpaceCommunalRepository extends JpaRepository<SpaceCommunalEntity, Integer> {
}
