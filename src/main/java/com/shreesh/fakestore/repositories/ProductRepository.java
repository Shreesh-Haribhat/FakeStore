package com.shreesh.fakestore.repositories;

import com.shreesh.fakestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //public Optional<Product> findById(Long id);

    List<Product> findAll();

    public Product save(Product product);

    @Query("select p from Product p where p.id = :shreesh")
    public Product doSomething(@Param("shreesh") Long id);

    
}
