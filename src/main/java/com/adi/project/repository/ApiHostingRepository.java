package com.adi.project.repository;

import com.adi.project.model.ApiHosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiHostingRepository extends JpaRepository<ApiHosting, Long> {
    boolean existsByName(String name);
}
