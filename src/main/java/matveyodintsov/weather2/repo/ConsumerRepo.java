package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Consumer;
import matveyodintsov.weather2.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer, Long> {

    Optional<Consumer> findByName(String name);
    boolean existsByNameIgnoreCase(String name);

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
