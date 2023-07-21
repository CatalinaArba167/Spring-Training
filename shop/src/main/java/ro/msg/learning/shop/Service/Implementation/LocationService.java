package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.LocationSimpleDto;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Exception.LocationAlreadyExistingexception;
import ro.msg.learning.shop.Exception.LocationNotFoundException;
import ro.msg.learning.shop.Exception.ProductAlreadyExistingException;
import ro.msg.learning.shop.Exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.Mapper.LocationSimpleTransferMapper;
import ro.msg.learning.shop.Mapper.ProductAndProductCategoryTranslatorMapper;
import ro.msg.learning.shop.Repository.ILocationRepository;
import ro.msg.learning.shop.Repository.IProductCategoryRepository;

import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    private ILocationRepository locationRepository;

    public LocationSimpleDto createLocation(LocationSimpleDto locationSimpleDto) {
        if(locationRepository.findByName(locationSimpleDto.getName())!=null)
        {
            throw new LocationAlreadyExistingexception(locationSimpleDto.getName());
        }
        LocationSimpleTransferMapper mapper=new LocationSimpleTransferMapper();
        Location location = mapper.toLocation(locationSimpleDto);
        Location savedLocation = locationRepository.save(location);
        return mapper.toLocationSimpleDto(savedLocation);
    }

    public Location findById(UUID id){
        Location location=this.locationRepository.findById(id);
        if(location==null)
        {
            throw new LocationNotFoundException(id);
        }
        return location;
    }

}
