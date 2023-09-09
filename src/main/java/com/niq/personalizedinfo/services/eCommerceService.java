package com.niq.personalizedinfo.services;

import com.niq.personalizedinfo.schema.product.ProductRepository;
import com.niq.personalizedinfo.schema.shopper.ShopperRepository;
import com.niq.personalizedinfo.schema.shopperproduct.ShopperProduct;
import com.niq.personalizedinfo.schema.shopperproduct.ShopperProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class eCommerceService {
    private final EntityManager entityManager;
    ProductRepository productRepository;
    ShopperRepository shopperRepository;
    ShopperProductRepository shopperProductRepository;

    @Autowired
    public eCommerceService(ProductRepository productRepository,
                            ShopperRepository shopperRepository,
                            ShopperProductRepository shopperProductRepository,
                            EntityManager entityManager) {
        this.productRepository = productRepository;
        this.shopperRepository = shopperRepository;
        this.shopperProductRepository = shopperProductRepository;
        this.entityManager = entityManager;
    }

    public List<String> getProductsByShopper(String shopperId, String category, String brand, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<ShopperProduct> root = query.from(ShopperProduct.class);

        query.select(root.get("productId"));

        Predicate predicate = criteriaBuilder.equal(root.get("shopperId"), shopperId);

        if (category != null && !category.isEmpty()) {
            predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("product").get("category"), category), predicate);
        }

        if (brand != null && !brand.isEmpty()) {
            predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("product").get("brand"), brand), predicate);
        }

        query.where(predicate);

        query.groupBy(root.get("productId"));

        entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0);

        return entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0).getResultList();
    }

    public List<String> getShoppersByProduct(String productId, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<ShopperProduct> root = query.from(ShopperProduct.class);

        query.select(root.get("shopperId"));

        Predicate predicate = criteriaBuilder.equal(root.get("productId"), productId);

        query.where(predicate);

        query.groupBy(root.get("shopperId"));

        entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0);

        return entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0).getResultList();
    }

}
