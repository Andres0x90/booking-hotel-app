package co.edu.tdea.infrastructure.repositories;

import co.edu.tdea.infrastructure.data.BookingData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends CrudRepository<BookingData, String> {
    @Query(value = "SELECT * FROM booking WHERE room_id = ?1", nativeQuery = true)
    Iterable<BookingData> findByRoomId(String roomId);

    @Query(value="SELECT * FROM booking b INNER JOIN room r ON b.room_id = r.id WHERE r.type = ?", nativeQuery = true)    Iterable<BookingData> findAvailable(int roomType);

}
