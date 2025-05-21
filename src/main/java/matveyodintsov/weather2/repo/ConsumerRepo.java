package matveyodintsov.weather2.repo;

import matveyodintsov.weather2.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer, Long> {

    public Optional<Consumer> findByName(String name);
    public boolean existsByNameIgnoreCase(String name);

}
