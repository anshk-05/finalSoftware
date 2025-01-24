package com.example.erp.Service;

import com.example.erp.Model.Product;
import com.example.erp.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(Product existingProduct, Product productDetails) {
        existingProduct.setProductName(productDetails.getProductName());
        existingProduct.setCategory(productDetails.getCategory());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setStockLevel(productDetails.getStockLevel());
        existingProduct.setReorderLevel(productDetails.getReorderLevel());
        existingProduct.setLastPurchaseDate(productDetails.getLastPurchaseDate());
        existingProduct.setStore(productDetails.getStore());
        existingProduct.setSupplier(productDetails.getSupplier());
        return productRepository.save(existingProduct);
    }

    // Delete a product
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    // Get products below reorder level
    public List<Product> getProductsBelowReorderLevel() {
        return productRepository.findProductsBelowReorderLevel();
    }

    // Get products by supplier ID
    public List<Product> getProductsBySupplier(Integer supplierId) {
        return productRepository.findBySupplier_SupplierId(supplierId);
    }

    // Update last purchase date for a product
    public Product updateLastPurchaseDate(Integer productId, LocalDate lastPurchaseDate) {
        Product product = getProductById(productId);
        product.setLastPurchaseDate(lastPurchaseDate);
        return productRepository.save(product);
    }
}
