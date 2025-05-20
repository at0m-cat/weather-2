package matveyodintsov.weather2;

import matveyodintsov.weather2.service.WeatherService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Weather2Application {

    public static void main(String[] args) {
        SpringApplication.run(Weather2Application.class, args);
    }

}
