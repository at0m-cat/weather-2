package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

    boolean existsLocationByLonAndLat(double lon, double lat);
}
