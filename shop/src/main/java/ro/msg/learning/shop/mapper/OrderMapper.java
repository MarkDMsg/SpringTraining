package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;

import java.util.List;

@Component
public class OrderMapper {
    public OrderWithProductsDto toOrderWithProductsDto(Order order, List<Product> products, List<Integer> quantities){
        return new OrderWithProductsDto(order.getId(),order.getCustomerId(),order.getCreatedAt(),products,quantities,order.getAddress().getCountry(),order.getAddress().getCity(),order.getAddress().getCounty(),order.getAddress().getStreetAddress());
    }
}
