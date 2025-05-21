package matveyodintsov.weather2.config;

import matveyodintsov.weather2.mapper.LocationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public LocationMapper locationMapper() {
        return new LocationMapper();
    }

}
