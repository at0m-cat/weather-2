package matveyodintsov.weather2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openweather")
@Getter
@Setter
public class OpenWeatherClientConfig {
    private String weather;
    private String location;
    private String key;
}
