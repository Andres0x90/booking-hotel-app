package co.edu.tdea.domain.services;

import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Booking;
import co.edu.tdea.domain.models.Types;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;

public interface BookingService {

    Completable createRoom(RoomDTO roomDTO) throws IllegalArgumentException;
    Single<Booking> bookRoom(Booking booking);
    Completable deleteBooking(String code);
    Flowable<Booking> getHistoryPerRoom(Integer roomId);
    Flowable<RoomDTO> getAvailableByType(String type);
}
