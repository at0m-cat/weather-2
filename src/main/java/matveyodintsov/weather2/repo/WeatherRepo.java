package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepo extends JpaRepository<Weather, Long> {
}
