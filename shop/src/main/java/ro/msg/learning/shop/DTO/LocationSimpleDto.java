package ro.msg.learning.shop.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class LocationSimpleDto {
    private UUID id;
    private String name;
    private String country;
    private String city;
    private String county;
    private String address;
}
