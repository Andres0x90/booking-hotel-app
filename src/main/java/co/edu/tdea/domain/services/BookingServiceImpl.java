package co.edu.tdea.domain.services;

import co.edu.tdea.domain.dto.Mapper;
import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.*;
import co.edu.tdea.infrastructure.data.FineData;
import co.edu.tdea.infrastructure.repositories.BookingRepository;
import co.edu.tdea.infrastructure.repositories.RoomRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private Mapper mapper;
    @Override
    public Completable createRoom(RoomDTO roomDTO) throws IllegalArgumentException {

        return Maybe.just(roomRepository.findById(roomDTO.getId()))
                .map(r -> {
                    if(r.isPresent()) {
                        throw new RuntimeException("Error: Room already exist on dbs");
                    }
                    return roomDTO;
                })
                .map(r-> {
                    Types type = Types.valueOf(r.getType().toUpperCase());
                    Room room = new Room(r.getId(),type);
                    return roomRepository.save(room);
                })

                .flatMapCompletable(complete -> Completable.complete());
    }

    @Override
    public Single<Booking> bookRoom(Booking booking) {
        return Single.just(roomRepository.findById(booking.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("That room doesnt exist")))
                .map(room -> booking)
                .map(booking1 -> {
                    booking1.calculateFee();
                    return booking1;
                })
                .map(mapper::toData)
                .map(bookingRepository::save)
                .map(mapper::toEntity);
    }

    @Override
    public Completable deleteBooking(String code) {
        return Single.just(bookingRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("That booking doesnt exist")))
                .map(bookingData -> {
                    long days = ChronoUnit.DAYS.between(LocalDate.now(),
                            bookingData.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    if (days <= 5) {
                        bookingData.getClient().getFines()
                                .add(FineData.builder()
                                        .fee(bookingData.getFee().multiply(BigDecimal.valueOf( 0.05)))
                                        .build());

                        bookingRepository.save(bookingData);
                    }
                    bookingRepository.deleteById(code);
                    return bookingData;
                }).flatMapCompletable(bookingData -> Completable.complete());
    }

    @Override
    public Flowable<Booking> getHistoryPerRoom(Integer roomId) {
        return Flowable.fromIterable(bookingRepository.findAllByRoomId(roomId))
                .map(mapper::toEntity);
    }
}
