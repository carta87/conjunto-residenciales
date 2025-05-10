package com.microservice.auth.jpa.repository;

import com.microservice.auth.jpa.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<CommentEntity, Integer> {}
