package com.niq.personalizedinfo.schema.shopper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, String> {
    boolean existsByShopperIdContainsIgnoreCase(@NonNull String shopperId);
}
