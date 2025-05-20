package matveyodintsov.weather2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openweather")
@Getter
@Setter
public class OpenWeatherConfig {
    private String url;
    private String key;
}
