package matveyodintsov.weather2.mapper;

import matveyodintsov.weather2.dto.LocationDto;
import matveyodintsov.weather2.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    LocationDto toDto(Location location);

    Location fromDto(LocationDto locationDto);


}
