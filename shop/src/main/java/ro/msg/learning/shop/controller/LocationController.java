package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;

@RequestMapping("/location")
@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    private final LocationMapper locationMapper;

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {

        Location location = locationMapper.toLocation(locationDto);
        LocationDto returnedLocationDto = locationService.createLocation(location);
        return new ResponseEntity<>(returnedLocationDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> returnedLocations = locationService.getAllDtoLocations();
        return new ResponseEntity<>(returnedLocations, HttpStatus.OK);
    }
}
