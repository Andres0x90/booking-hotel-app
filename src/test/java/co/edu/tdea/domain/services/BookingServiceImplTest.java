package co.edu.tdea.domain.services;

import co.edu.tdea.domain.dto.Mapper;
import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Booking;
import co.edu.tdea.domain.models.Client;
import co.edu.tdea.domain.models.Room;
import co.edu.tdea.domain.models.Types;
import co.edu.tdea.infrastructure.data.BookingData;
import co.edu.tdea.infrastructure.data.ClientData;
import co.edu.tdea.infrastructure.repositories.BookingRepository;
import co.edu.tdea.infrastructure.repositories.RoomRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest extends TestCase {

    @Mock
    RoomRepository roomRepository;
    @Mock
    BookingRepository bookingRepository;

    Mapper mapper = new Mapper();

    @InjectMocks
    BookingServiceImpl bookingService;

    @Test
    public void testCreateRoom() {
        Mockito.when(roomRepository.save(Mockito.any())).thenReturn(new Room());
        bookingService.createRoom(RoomDTO.builder().id(1).type("EXECUTIVE").build())
                .test().assertComplete();
    }

    @Test
    public void testBookRoom() {
        bookingService.setMapper(mapper);
        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.of(new Room(1, Types.EXECUTIVE)));
        Mockito.when(bookingRepository.save(Mockito.any())).thenReturn(BookingData.builder()
                        .client(ClientData.builder().document("123").fines(List.of()).build())
                .build());

        bookingService.bookRoom(Booking.builder()
                        .startDate(new Date())
                        .endDate(new Date())
                        .client(Client.builder().document("123").fines(List.of()).build())
                        .room(new Room(1, Types.EXECUTIVE)).build())
                .test()
                .assertValueCount(1)
                .assertComplete();
    }

    public void testDeleteBooking() {
    }

    public void testGetHistoryPerRoom() {
    }
}