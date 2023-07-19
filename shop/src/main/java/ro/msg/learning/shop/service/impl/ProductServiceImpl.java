package ro.msg.learning.shop.service.impl;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    int INITIAL_NR_OF_PRODUCTS=0;

    private final ProductMapper productMapper=ProductMapper.INSTANCE;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductWithCategoryDto createProduct(String name, String description, BigDecimal price, Double weight, String supplier, String imageUrl, ProductCategory productCategory) {
        Product product= Product.builder().name(name).description(description).price(price).weight(weight).supplier(supplier).imageUrl(imageUrl).category(productCategory).build();
        return productMapper.toDTO(product);
    }

    @Override
    public ProductWithCategoryDto updateProduct(UUID productId, String newName, String newDescription, BigDecimal newPrice, Double newWeight, String newSupplier, String newImageUrl, ProductCategory newCategory) {
        Optional<Product> newProduct=productRepository.findById(productId);
        if(newProduct.isPresent()){
            newProduct.get().setName(newName);
            newProduct.get().setDescription(newDescription);
            newProduct.get().setPrice(newPrice);
            newProduct.get().setWeight(newWeight);
            newProduct.get().setSupplier(newSupplier);
            newProduct.get().setImageUrl(newImageUrl);
            newProduct.get().setCategory(newCategory);
            return productMapper.toDTO(newProduct.get());
        }
       else return null;

    }

    @Override
    public void deleteProductById(UUID productId) {

        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
                productRepository.deleteById(product.get().getId());
        }

    }

    @Override
    public ProductWithCategoryDto getProductById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return productMapper.toDTO(product.get());
        }else return null;
    }

    @Override
    public List<ProductWithCategoryDto> getAllProducts() {
        return (List<ProductWithCategoryDto>)productRepository.findAll().stream()
                .map(productMapper::toDTO).collect(Collectors.toList());
    }

}
