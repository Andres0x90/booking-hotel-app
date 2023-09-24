package co.edu.tdea.infrastructure.repositories;

import co.edu.tdea.infrastructure.data.BookingData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<BookingData, String> {
}
