package co.edu.tdea.api.controllers;

import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Types;
import co.edu.tdea.domain.services.BookingService;
import io.reactivex.rxjava3.core.Completable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return bookingService.bookRoom(roomDTO);

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



}
