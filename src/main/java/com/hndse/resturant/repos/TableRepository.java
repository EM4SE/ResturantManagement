package com.hndse.resturant.repos;

import com.hndse.resturant.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TableRepository extends JpaRepository<Tables, Integer> {
    boolean existsByNumber(Integer number);
}
