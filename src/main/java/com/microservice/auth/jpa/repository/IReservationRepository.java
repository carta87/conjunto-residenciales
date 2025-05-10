package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<ReservationEntity, Integer> {
}
