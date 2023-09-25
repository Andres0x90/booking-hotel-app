package co.edu.tdea.api.controllers;

import co.edu.tdea.domain.dto.AvailableDTO;
import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Booking;
import co.edu.tdea.domain.models.Types;
import co.edu.tdea.domain.services.BookingService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@Getter
@Setter
@RequestMapping("/bookinghotel")
public class BookingController {

    @Autowired
    BookingService bookingService;

    private Types types;

    @PostMapping("/createRoom")
    public Completable createRoom(@RequestBody RoomDTO roomDTO){
        return bookingService.createRoom(roomDTO);
    }

    @PostMapping("/book-room")
    public Single<Booking> createBooking(@RequestBody Booking booking){
        return bookingService.bookRoom(booking);
    }

    @DeleteMapping("/book-room/{code}")
    public Completable deleteBooking(@PathVariable String code){
        return bookingService.deleteBooking(code);
    }

    @GetMapping("/room/history/{roomId}")
    public Flowable<Booking> getHistory(@PathVariable Integer roomId){
        return bookingService.getHistoryPerRoom(roomId);
    }

    @GetMapping("/room/available/{type}")
    public Flowable<RoomDTO> getAvailableByType(@PathVariable String type){
        return  bookingService.getAvailableByType(type);
    }
}
