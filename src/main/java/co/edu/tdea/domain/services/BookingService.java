package co.edu.tdea.domain.services;

import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Booking;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface BookingService {

    Completable createRoom(RoomDTO roomDTO) throws IllegalArgumentException;
    Single<Booking> bookRoom(Booking booking);
}
