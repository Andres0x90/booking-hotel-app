package co.edu.tdea.infrastructure.repositories;

import co.edu.tdea.infrastructure.data.BookingData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<BookingData, String> {
    @Query(value = "SELECT * FROM booking WHERE room_id = ?1", nativeQuery = true)
    Iterable<BookingData> findByRoomId(String roomId);
}
