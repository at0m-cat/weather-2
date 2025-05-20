package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepo extends JpaRepository<Weather, Long> {
}
