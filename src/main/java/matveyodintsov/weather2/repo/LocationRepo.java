package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
