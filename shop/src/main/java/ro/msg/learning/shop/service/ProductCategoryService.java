package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        if(!productCategoryRepository.existsByName(productCategory.getName())){
            productCategoryRepository.save(productCategory);
            return productCategory;
        }
        else
            return productCategoryRepository.findByName(productCategory.getName());


    }
}
