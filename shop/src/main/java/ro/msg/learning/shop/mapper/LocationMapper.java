package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.OrderWithProductsDto;

import java.util.List;

@Component
public class LocationMapper {
    public LocationDto toLocationDto(Location location){
        return new LocationDto(location.getId(), location.getName(),location.getAddress().getCountry(),location.getAddress().getCity(),location.getAddress().getCounty(),location.getAddress().getStreetAddress());
    }

    public Location toLocation(LocationDto locationDto){
        Address address=new Address(locationDto.getCountry(),locationDto.getCity(),locationDto.getCounty(),locationDto.getStreetAddress());
        Location location=new Location(locationDto.getName(),address);
        return location;
    }
}
