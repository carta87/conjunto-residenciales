package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuRepository extends JpaRepository<MenuEntity, Long> {
}
