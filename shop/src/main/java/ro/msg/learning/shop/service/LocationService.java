package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    LocationRepository locationRepository;

    public LocationDto createLocation(LocationDto locationDto){
        Location location= locationMapper.toLocation(locationDto);
        locationRepository.save(location);
        return locationMapper.toLocationDto(location);
    }

    public Location getLocationEntityById(UUID id){
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    public List<LocationDto> getAllDtoLocations(){
        return locationRepository.findAll().stream().map(e->locationMapper.toLocationDto(e)).toList();
    }
}
