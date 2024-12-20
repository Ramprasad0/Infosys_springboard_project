package com.example.module2.repository;

import com.example.module2.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends JpaRepository<Flat,Long> {
    Flat findByFlatNo(String flatNo);
}