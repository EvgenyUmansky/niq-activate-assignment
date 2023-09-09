package com.niq.personalizedinfo.controllers;

import com.niq.personalizedinfo.services.eCommerceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/e-commerce")
@Tag(name = "E-Commerce", description = "E-Commerce management APIs")
public class eCommerceController {
    eCommerceService commerceService;

    @Autowired
    public eCommerceController(eCommerceService commerceService) {
        this.commerceService = commerceService;
    }

    /**
     * Get Products by shopper (with filters)
     *
     * @param shopperId - unique id of shopper
     * @param category  - product category
     * @param brand     - product brand
     * @param limit     - page size
     * @return - product ids fit the given filters
     */
    @Operation(
            summary = "Get Products by shopper and filters",
            tags = {"products", "shoppers", "get"})
    @GetMapping(value = "products-by-shopper/get")
    public List<String> getProductsByShopper(@RequestParam("shopperId") @Parameter(name = "shopperId", description = "Shopper Id ", example = "S-1000") String shopperId,
                                             @RequestParam(value = "category", required = false) @Parameter(name = "category", description = "Category name", example = "Milk") String category,
                                             @RequestParam(value = "brand", required = false) @Parameter(name = "brand", description = "Brand name", example = "Juice") String brand,
                                             @RequestParam(value = "limit", required = false, defaultValue = "10") @Parameter(name = "limit", description = "Page size", example = "10") Integer limit) {
        try {
            List<String> products = commerceService.getProductsByShopper(shopperId, category, brand, limit);

            // TODO: AOP
            String response = "[getProductsByShopper] Product List has been added successfully";
            log.info(response);

            return products;
        } catch (Exception e) {
            // TODO: AOP, exception handler
            String response = "[getProductsByShopper] Failed to get Product List, response code: %d".formatted(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            log.error(response, e);
            return null;
        }
    }

    /**
     * Get shoppers by product
     *
     * @param productId unique id of product
     * @param limit     - page size
     * @return - shopper ids fit the given filters
     */
    @Operation(
            summary = "Get shoppers by product",
            tags = {"products", "shoppers", "get"})
    @GetMapping(value = "shoppers-by-products/get")
    public List<String> getShoppersByProduct(@RequestParam("productId") @Parameter(name = "productId", description = "Product id", example = "SS-1529984359") String productId,
                                             @RequestParam(value = "limit", required = false, defaultValue = "10") @Parameter(name = "limit", description = "Page size", example = "10") Integer limit) {
        try {
            List<String> shoppers = commerceService.getShoppersByProduct(productId, limit);

            // TODO: AOP
            String response = "[getShoppersByProduct] Product List has been added successfully";
            log.info(response);

            return shoppers;
        } catch (Exception e) {
            // TODO: AOP, exception handler
            String response = "[getShoppersByProduct] Failed to get Product List, response code: %d".formatted(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            log.error(response, e);
            return null;
        }
    }
}
