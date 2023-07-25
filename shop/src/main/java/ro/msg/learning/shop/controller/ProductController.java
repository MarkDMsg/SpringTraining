package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;
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

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductCategory productCategory = new ProductCategory(productDto.getCategoryName(), productDto.getCategoryDescription());
        productCategory = productCategoryService.saveProductCategory(productCategory);
        ProductDto returnedProductDto = productService.createProduct(productDto.getName(), productDto.getDescription(), productDto.getPrice(), productDto.getWeight(), productDto.getSupplier(), productDto.getImageUrl(), productCategory);
        return new ResponseEntity<>(returnedProductDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductDto newProduct) {
        ProductCategory productCategory = new ProductCategory(newProduct.getCategoryName(), newProduct.getCategoryDescription());
        ProductDto returnedProduct = productService.updateProduct(id, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getWeight(), newProduct.getSupplier(), newProduct.getImageUrl(), productCategory);
        return new ResponseEntity<>(returnedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") UUID id) {
        ProductDto returnedProduct = productService.getProductDtoById(id);
        return new ResponseEntity<>(returnedProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> returnedProducts = productService.getAllDtoProducts();
        return new ResponseEntity<>(returnedProducts, HttpStatus.OK);
    }


}
