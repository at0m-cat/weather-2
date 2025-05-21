package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WeatherRepo extends JpaRepository<Weather, Long> {

    @Query("""
            SELECT w FROM Weather w 
            WHERE w.location IN (
                SELECT l FROM Consumer c JOIN c.locations l 
                WHERE c.name = :username
            )
            ORDER BY w.dt DESC
            """)
    List<Weather> findWeatherByUsername(@Param("username") String username);
}
