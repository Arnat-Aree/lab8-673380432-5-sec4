package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.repository.ProductRepository;
import com.example.demo.strategy.DiscountContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DiscountContext discountContext;

    // Constructor Injection (DIP)
    public ProductService(ProductRepository productRepository, DiscountContext discountContext) {
        this.productRepository = productRepository;
        this.discountContext = discountContext;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            p.setDiscountedPrice(discountContext.calculatePrice(p.getDiscountType(), p.getPrice()));
        }
        return products;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public Product saveProduct(Product product) {
        // เชื่อม FK กลับ: ฝั่ง Review ต้องมี product อ้างกลับมา (FK อยู่ฝั่งนี้เสมอ)
        if (product.getReviews() != null) {
            product.getReviews().removeIf(r -> r.getReviewer() == null || r.getReviewer().isBlank());
            for (Review r : product.getReviews()) {
                r.setProduct(product);
                if (r.getReviewDate() == null) {
                    r.setReviewDate(LocalDate.now());
                }
            }
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setBrand(updatedProduct.getBrand());
        existing.setStock(updatedProduct.getStock());
        existing.setPrice(updatedProduct.getPrice());
        existing.setDiscountType(updatedProduct.getDiscountType());

        if (updatedProduct.getDetail() != null) {
            if (existing.getDetail() == null) {
                existing.setDetail(updatedProduct.getDetail());
            } else {
                existing.getDetail().setDescription(updatedProduct.getDetail().getDescription());
                existing.getDetail().setWarranty(updatedProduct.getDetail().getWarranty());
                existing.getDetail().setWeight(updatedProduct.getDetail().getWeight());
                existing.getDetail().setDimensions(updatedProduct.getDetail().getDimensions());
                existing.getDetail().setManufacturedCountry(updatedProduct.getDetail().getManufacturedCountry());
            }
        }

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}