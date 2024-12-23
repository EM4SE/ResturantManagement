package com.hndse.resturant.repos;

import com.hndse.resturant.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);
}
