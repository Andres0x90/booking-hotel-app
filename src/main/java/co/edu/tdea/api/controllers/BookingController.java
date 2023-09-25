package co.edu.tdea.api.controllers;

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

        /*try{
            if(!Types.exist(roomDTO.getType())){
                return new ResponseEntity<>("Error: type doesn't exist or has a mistype error", HttpStatus.CONFLICT);
            }
            Completable res = bookingService.bookRoom(roomDTO);

            //if(res.status == 500){

            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }*/


        //return new ResponseEntity<>("Room created successfully", HttpStatus.CREATED);

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
    public Flowable<Booking> getHistory(@PathVariable String roomId){
        return bookingService.getHistoryPerRoom(roomId);
    }
}
