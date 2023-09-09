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
        // create criteria objects
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<ShopperProduct> root = query.from(ShopperProduct.class);

        // select only productId, there is no point to get all the object
        query.select(root.get("productId"));

        // build where condition, shopperId is required
        Predicate predicate = criteriaBuilder.equal(root.get("shopperId"), shopperId);

        // build where condition, category is optional
        if (category != null && !category.isEmpty()) {
            predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("product").get("category"), category), predicate);
        }

        // build where condition, brand is optional
        if (brand != null && !brand.isEmpty()) {
            predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("product").get("brand"), brand), predicate);
        }

        // add where to query
        query.where(predicate);

        // group by productId: no need duplicates
        query.groupBy(root.get("productId"));

        // set limit/page size, default 10 from controller
        // offset is always 0
        entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0);

        return entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0).getResultList();
    }

    public List<String> getShoppersByProduct(String productId, Integer limit) {
        // create criteria objects
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<ShopperProduct> root = query.from(ShopperProduct.class);

        // select only productId, there is no point to get all the object
        query.select(root.get("shopperId"));

        // build where condition, productId is required
        Predicate predicate = criteriaBuilder.equal(root.get("productId"), productId);

        // add where to query
        query.where(predicate);

        // group by shopperId: no need duplicates
        query.groupBy(root.get("shopperId"));

        // set limit/page size, default 10 from controller
        // offset is always 0
        entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0);

        return entityManager.createQuery(query).setMaxResults(limit).setFirstResult(0).getResultList();
    }

}
