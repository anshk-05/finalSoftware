package com.example.erp.Controller;

import com.example.erp.DTO.ProductDTO;
import com.example.erp.Model.Product;
import com.example.erp.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(product -> new ProductDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getStockLevel(),
                        product.getStore() != null ? product.getStore().getStoreName() : null,
                        product.getSupplier() != null ? product.getSupplier().getSupplierName() : null
                ))
                .collect(Collectors.toList());
    }

    // Get a single product
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return new ProductDTO(
                product.getProductId(),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getStockLevel(),
                product.getStore() != null ? product.getStore().getStoreName() : null,
                product.getSupplier() != null ? product.getSupplier().getSupplierName() : null
        );
    }

    // Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Update a product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        Product existingProduct = productService.getProductById(id);
        return productService.updateProduct(existingProduct, productDetails);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        productService.deleteProduct(product);
        return "Product with id " + id + " has been deleted.";
    }
}
