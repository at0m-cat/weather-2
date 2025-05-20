package matveyodintsov.weather2;

import org.springframework.boot.SpringApplication;

public class TestWeather2Application {

    public static void main(String[] args) {
        SpringApplication.from(Weather2Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
