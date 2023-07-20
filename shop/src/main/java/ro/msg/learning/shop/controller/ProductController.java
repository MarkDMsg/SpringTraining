package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.service.ProductCategoryService;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductWithCategoryDto> createProduct(@RequestBody ProductWithCategoryDto productWithCategoryDto){
        ProductCategory productCategory= new ProductCategory(productWithCategoryDto.getCategoryName(),productWithCategoryDto.getCategoryDescription());
        productCategoryService.saveProductCategory(productCategory);
        ProductWithCategoryDto returnedProductWithCategoryDto= productService.createProduct(productWithCategoryDto.getName(),productWithCategoryDto.getDescription(),productWithCategoryDto.getPrice(),productWithCategoryDto.getWeight(),productWithCategoryDto.getSupplier(),productWithCategoryDto.getImageUrl(),productCategory);
        return  new ResponseEntity<>(returnedProductWithCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductWithCategoryDto> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductWithCategoryDto newProduct){
        ProductCategory productCategory=new ProductCategory(newProduct.getCategoryName(), newProduct.getCategoryDescription());
        ProductWithCategoryDto returnedProduct= productService.updateProduct(id,newProduct.getName(),newProduct.getDescription(),newProduct.getPrice(),newProduct.getWeight(),newProduct.getSupplier(),newProduct.getImageUrl(),productCategory);
        return new ResponseEntity<>(returnedProduct,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductWithCategoryDto> getProductById(@PathVariable("id") UUID id){
       ProductWithCategoryDto returnedProduct=productService.getProductById(id);
       return new ResponseEntity<>(returnedProduct,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductWithCategoryDto>> getAllProducts(){
        List<ProductWithCategoryDto>  returnedProducts=productService.getAllProducts();
        return new ResponseEntity<>(returnedProducts,HttpStatus.OK);
    }


}
