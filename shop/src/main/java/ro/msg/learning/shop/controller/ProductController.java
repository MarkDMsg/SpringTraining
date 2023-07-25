package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.service.ProductCategoryService;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductCategoryService productCategoryService;

    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductCategory productCategory = new ProductCategory(productDto.getCategoryName(), productDto.getCategoryDescription());
        productCategoryService.saveProductCategory(productCategory);
        Product createdProduct = productMapper.toProduct(productDto);
        ProductDto returnedProductDto = productService.createProduct(createdProduct);
        return new ResponseEntity<>(returnedProductDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductDto newProduct) {
        Product newProductEntity = productMapper.toProduct(newProduct);
        ProductDto returnedProduct = productService.updateProduct(newProductEntity);
        return new ResponseEntity<>(returnedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") UUID id) {
        Product product = productService.getProductById(id);
        ProductDto returnedProduct = productMapper.toProductWithCategoryDto(product, product.getCategory());
        return new ResponseEntity<>(returnedProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> returnedProducts = productService.getAllProducts().stream().map(e -> productMapper.toProductWithCategoryDto(e, e.getCategory())).toList();
        return new ResponseEntity<>(returnedProducts, HttpStatus.OK);
    }


}
