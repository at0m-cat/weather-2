package matveyodintsov.weather2.mapper;

import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    LocationDto toDto(Location location);

    List<LocationDto> toDto(List<Location> locations);

    Location fromDto(LocationDto locationDto);

    List<Location> fromDto(List<LocationDto> locationDtos);

}
