package co.edu.tdea.infrastructure.repositories;

import co.edu.tdea.domain.models.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookingRepository extends CrudRepository<Room, Integer> {

    Optional<Room> findById(int id);


}
