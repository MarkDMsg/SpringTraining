package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductDto createProduct(Product product) {
        productRepository.save(product);
        return productMapper.toProductWithCategoryDto(product, product.getCategory());
    }

    public ProductDto updateProduct(Product newProduct) {
        Optional<Product> oldProduct = productRepository.findById(newProduct.getId());
        if (oldProduct.isPresent()) {
            oldProduct.get().setName(newProduct.getName());
            oldProduct.get().setDescription(newProduct.getDescription());
            oldProduct.get().setPrice(newProduct.getPrice());
            oldProduct.get().setWeight(newProduct.getWeight());
            oldProduct.get().setSupplier(newProduct.getSupplier());
            oldProduct.get().setImageUrl(newProduct.getImageUrl());
            oldProduct.get().setCategory(newProduct.getCategory());
            productRepository.save(newProduct);
            return productMapper.toProductWithCategoryDto(newProduct, newProduct.getCategory());
        } else return null;
    }

    public void deleteProductById(UUID productId) {
        if (verifyProductExistence(productId)) {
            productRepository.deleteById(productId);
        }
    }

    public Product getProductById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll().stream().toList();
    }

    public boolean verifyProductExistence(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent();
    }

}
