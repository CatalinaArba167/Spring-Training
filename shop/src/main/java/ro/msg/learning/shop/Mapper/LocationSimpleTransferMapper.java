package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.LocationSimpleDto;
import ro.msg.learning.shop.Domain.Location;

@Component
public class LocationSimpleTransferMapper {
    public static LocationSimpleDto toLocationSimpleDto(Location location) {
        LocationSimpleDto dto = new LocationSimpleDto();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setCountry(location.getCountry());
        dto.setCity(location.getCity());
        dto.setCounty(location.getCounty());
        dto.setAddress(location.getAddress());
        return dto;
    }

    public static Location toLocation(LocationSimpleDto dto) {
        Location location = new Location();
        location.setId(dto.getId());
        location.setName(dto.getName());
        location.setCountry(dto.getCountry());
        location.setCity(dto.getCity());
        location.setCounty(dto.getCounty());
        location.setAddress(dto.getAddress());
        return location;
    }
}
