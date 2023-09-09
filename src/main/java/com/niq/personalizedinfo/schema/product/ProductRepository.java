package com.niq.personalizedinfo.schema.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByProductIdContainsIgnoreCaseAllIgnoreCase(@NonNull String productId);
}
