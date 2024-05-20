package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Dinosaur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DinosaurRepository extends CrudRepository<Dinosaur, Long> {
}
