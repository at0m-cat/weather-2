package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WeatherRepo extends JpaRepository<Weather, Long> {

    Optional<Weather> findByLocationId(Long id);
}
