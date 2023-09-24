package co.edu.tdea.domain.services;

import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Room;
import io.reactivex.rxjava3.core.Completable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    Completable bookRoom(RoomDTO roomDTO) throws IllegalArgumentException;

}
