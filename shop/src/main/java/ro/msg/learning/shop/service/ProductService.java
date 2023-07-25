package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    int INITIAL_NR_OF_PRODUCTS = 0;

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductDto createProduct(String name, String description, BigDecimal price, Double weight, String supplier, String imageUrl, ProductCategory productCategory) {
        Product product = Product.builder().name(name).description(description).price(price).weight(weight).supplier(supplier).imageUrl(imageUrl).category(productCategory).build();
        productRepository.save(product);
        return productMapper.toProductWithCategoryDto(product, productCategory);
    }

    public ProductDto updateProduct(UUID productId, String newName, String newDescription, BigDecimal newPrice, Double newWeight, String newSupplier, String newImageUrl, ProductCategory newCategory) {
        Optional<Product> newProduct = productRepository.findById(productId);
        if (newProduct.isPresent()) {
            newProduct.get().setName(newName);
            newProduct.get().setDescription(newDescription);
            newProduct.get().setPrice(newPrice);
            newProduct.get().setWeight(newWeight);
            newProduct.get().setSupplier(newSupplier);
            newProduct.get().setImageUrl(newImageUrl);
            newProduct.get().setCategory(newCategory);
            productRepository.save(newProduct.get());
            return productMapper.toProductWithCategoryDto(newProduct.get(), newCategory);
        } else return null;

    }

    public void deleteProductById(UUID productId) {
        if (verifyProductExistence(productId)) {
            productRepository.deleteById(productId);
        }

    }

    public Product getProductEntityById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    public ProductDto getProductDtoById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(value -> productMapper.toProductWithCategoryDto(value, value.getCategory())).orElse(null);
    }

    public List<ProductDto> getAllDtoProducts() {

        return productRepository.findAll().stream()
                .map(element -> productMapper.toProductWithCategoryDto(element, element.getCategory())).toList();
    }

    public boolean verifyProductExistence(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent();
    }

}
