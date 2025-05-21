package matveyodintsov.weather2.config;

import matveyodintsov.weather2.adapter.LocationAdapter;
import matveyodintsov.weather2.adapter.WeatherAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    public LocationAdapter locationAdapter() {
        return new LocationAdapter();
    }

    @Bean
    public WeatherAdapter weatherAdapter() {
        return new WeatherAdapter();
    }

}
