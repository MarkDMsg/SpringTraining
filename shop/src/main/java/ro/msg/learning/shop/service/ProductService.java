package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
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

    private final ProductCategoryService productCategoryService;

    public ProductDto createProduct(Product product) {
        ProductCategory productCategory = ProductCategory.builder().name(product.getCategory().getName()).description(product.getCategory().getDescription()).build();
        productCategoryService.saveProductCategory(productCategory);
        productRepository.save(product);
        return productMapper.toProductWithCategoryDto(product, product.getCategory());
    }

    public ProductDto updateProduct(Product newProduct) {
        Optional<Product> oldProduct = productRepository.findById(newProduct.getId());
        if (oldProduct.isPresent()) {
            productRepository.save(newProduct);
            return productMapper.toProductWithCategoryDto(newProduct, newProduct.getCategory());
        }
        return null;
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
