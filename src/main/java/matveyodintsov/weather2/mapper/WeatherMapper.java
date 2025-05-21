package matveyodintsov.weather2.mapper;

import matveyodintsov.weather2.dto.WeatherDto;
import matveyodintsov.weather2.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherMapper {

    WeatherDto toWeatherDto(Weather weather);

    Weather toWeather(WeatherDto weatherDto);

}
