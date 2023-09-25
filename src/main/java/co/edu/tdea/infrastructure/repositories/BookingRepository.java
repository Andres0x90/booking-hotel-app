package co.edu.tdea.infrastructure.repositories;

import co.edu.tdea.domain.models.Types;
import co.edu.tdea.infrastructure.data.BookingData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends CrudRepository<BookingData, String> {
    List<BookingData> findAllByRoomId(Integer roomId);
    List<BookingData> findAllByRoomType(Types type);
}
