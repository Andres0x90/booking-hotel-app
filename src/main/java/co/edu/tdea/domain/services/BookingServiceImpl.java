package co.edu.tdea.domain.services;

import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Room;
import co.edu.tdea.domain.models.Types;
import co.edu.tdea.domain.services.BookingService;
import co.edu.tdea.infrastructure.repositories.BookingRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Completable bookRoom(RoomDTO roomDTO) throws IllegalArgumentException {

        return Maybe.just(bookingRepository.findById(roomDTO.getId()))
                .map(r -> {
                    if(r.isPresent()) {
                        throw new Exception("Error: Room already exist on dbs");
                    }
                    return roomDTO;
                })
                .map(r-> {
                    Types type = Types.valueOf(r.getType().toUpperCase());
                    Room room = new Room(r.getId(),type);
                    return bookingRepository.save(room);
                })

                .flatMapCompletable(complete -> Completable.complete());
    }
}
