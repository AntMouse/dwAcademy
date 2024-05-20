package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
