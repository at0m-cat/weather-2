package matveyodintsov.weather2.config;

import matveyodintsov.weather2.adapter.LocationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    public LocationAdapter locationMapper() {
        return new LocationAdapter();
    }

}
