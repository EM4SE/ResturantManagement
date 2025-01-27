package com.hndse.resturant.repos;

import com.hndse.resturant.entities.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierRepository extends JpaRepository<Cashier,Integer> {
    boolean existsByUsername(String username);
    boolean existsById(int id);
    Cashier findByUsername(String username);
}
