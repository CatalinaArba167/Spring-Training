package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.LocationSimpleDto;
import ro.msg.learning.shop.Domain.Location;

@Component
public class LocationSimpleTransferMapper {
    public static LocationSimpleDto toDto(Location location) {
        return LocationSimpleDto.builder()
                .id(location.getId())
                .name(location.getName())
                .country(location.getCountry())
                .city(location.getCity())
                .county(location.getCounty())
                .address(location.getAddress())
                .build();
    }

    public static Location toEntity(LocationSimpleDto dto) {
        return Location.builder()
                .name(dto.getName())
                .country(dto.getCountry())
                .city(dto.getCity())
                .county(dto.getCounty())
                .address(dto.getAddress())
                .build();
    }
}
