package com.hndse.resturant.repos;

import com.hndse.resturant.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TableRepository extends JpaRepository<Tables, Integer> {
    boolean existsByNumber(Integer number);

    @Modifying
    @Query("UPDATE Tables t SET t.status = :status WHERE t.number = :tableNumber")
    void updateTableStatus(@Param("tableNumber") Integer tableNumber,
                           @Param("status") String status);

    @Query("SELECT t.status FROM Tables t WHERE t.number = :tableNumber")
    String findTableStatus(@Param("tableNumber") Integer tableNumber);
}
