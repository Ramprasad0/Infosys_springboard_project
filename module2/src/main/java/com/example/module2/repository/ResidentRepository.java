package com.example.module2.repository;

import com.example.module2.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Long> {
    Resident findByEmail(String email);
}
