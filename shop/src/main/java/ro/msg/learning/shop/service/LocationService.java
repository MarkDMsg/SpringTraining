package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;

    public LocationDto createLocation(Location location) {
        locationRepository.save(location);
        return locationMapper.toLocationDto(location);
    }

    public Location getLocationById(UUID id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll().stream().toList();
    }
}
