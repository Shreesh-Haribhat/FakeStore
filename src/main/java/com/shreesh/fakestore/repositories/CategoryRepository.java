package com.shreesh.fakestore.repositories;

import com.shreesh.fakestore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category save(Category category);

    public Optional<Category> findByName(String name);
}
