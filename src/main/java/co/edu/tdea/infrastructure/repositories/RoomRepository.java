package co.edu.tdea.infrastructure.repositories;

import co.edu.tdea.domain.models.Room;
import co.edu.tdea.domain.models.Types;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    Optional<Room> findById(int id);
    List<Room> findAllByType(Types types);

}
