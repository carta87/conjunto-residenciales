package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApartmentRepository extends JpaRepository<ApartmentEntity, Long> {
}