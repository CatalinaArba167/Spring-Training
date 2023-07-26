package ro.msg.learning.shop.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.LocationSimpleDto;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Exception.AlreadyExistsException;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Mapper.LocationSimpleTransferMapper;
import ro.msg.learning.shop.Repository.ILocationRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {
    public static final String LOCATION_NOT_FOUND = "Location not found: ";
    public static final String LOCATION_ALREADY_EXISTS = "Location already exists: ";
    private final ILocationRepository locationRepository;

    public LocationSimpleDto createLocation(LocationSimpleDto locationSimpleDto) {
        Optional<Location> location = locationRepository.findByName(locationSimpleDto.getName());
        if (location.isPresent()) {
            throw new AlreadyExistsException(LOCATION_ALREADY_EXISTS + locationSimpleDto.getName());
        }
        Location newLocation = LocationSimpleTransferMapper.toEntity(locationSimpleDto);
        Location savedLocation = locationRepository.save(newLocation);
        return LocationSimpleTransferMapper.toDto(savedLocation);
    }

    public Location findById(UUID id) {
        Optional<Location> location = this.locationRepository.findById(id);
        if (location.isPresent()) {
            throw new NotFoundException(LOCATION_NOT_FOUND + id);
        }
        return location.get();
    }

}
