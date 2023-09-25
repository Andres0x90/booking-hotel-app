package co.edu.tdea.domain.services;

import co.edu.tdea.domain.dto.Mapper;
import co.edu.tdea.domain.dto.RoomDTO;
import co.edu.tdea.domain.models.Booking;
import co.edu.tdea.domain.models.Client;
import co.edu.tdea.domain.models.Room;
import co.edu.tdea.domain.models.Types;
import co.edu.tdea.infrastructure.data.BookingData;
import co.edu.tdea.infrastructure.data.ClientData;
import co.edu.tdea.infrastructure.data.FineData;
import co.edu.tdea.infrastructure.repositories.BookingRepository;
import co.edu.tdea.infrastructure.repositories.RoomRepository;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import junit.framework.TestCase;
import org.hibernate.mapping.Any;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest extends TestCase {

    @Mock
    RoomRepository roomRepository;
    @Mock
    BookingRepository bookingRepository;

    @Mock
    Mapper mapper = new Mapper();

    @InjectMocks
    BookingServiceImpl bookingService;

    @Test
    public void testCreateRoom() {
        when(roomRepository.save(Mockito.any())).thenReturn(new Room());
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

    @Test
    public void testDeleteBooking() {
        // Arrange
        String bookingCode = "001";
        BookingData bookingData = new BookingData(); // Crea una instancia de BookingData con los datos necesarios
        bookingData.setStartDate(java.util.Date.from(LocalDate.now().minusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        bookingData.setFee(BigDecimal.valueOf(100.0));
        ClientData clientDataMock = Mockito.mock(ClientData.class);
        //clientData.setFines(new ArrayList<>());
        bookingData.setClient(clientDataMock);

        when(bookingRepository.findById(bookingCode)).thenReturn(Optional.of(bookingData));

        Completable result = bookingService.deleteBooking(bookingCode);

        // Assert
        result.test()
                .assertComplete();

        verify(bookingRepository, times(1)).findById(bookingCode);

        verify(bookingRepository, times(1)).deleteById(bookingCode);
    }

    @Test
    public void testGetHistoryPerRoom() {
        String bookingCode = "001";
        BookingData bookingData = new BookingData(); // Crea una instancia de BookingData con los datos necesarios
        bookingData.setStartDate(java.util.Date.from(LocalDate.now().minusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        bookingData.setFee(BigDecimal.valueOf(100.0));
        ClientData clientDataMock = Mockito.mock(ClientData.class);
        //clientData.setFines(new ArrayList<>());
        bookingData.setClient(clientDataMock);

        // Arrange
        Integer roomId = 1;
        List<BookingData> bookingDataList = List.of(
                bookingData
        );

        when(bookingRepository.findAllByRoomId(roomId)).thenReturn(bookingDataList);

        // Supongamos que tu mapper convierte BookingData en Booking
        when(mapper.toEntity(bookingData)).thenAnswer(invocation -> {
            BookingData nBookingData = invocation.getArgument(0);
            if (nBookingData == null) {
                // Maneja el caso en el que bookingData es nulo
                // Puedes lanzar una excepción o tomar la acción adecuada
                throw new IllegalArgumentException("bookingData no debe ser nulo");
            }
            // Verifica que bookingData no sea nulo antes de acceder a sus propiedades
            String code = nBookingData.getCode(); // Acceder a getCode() después de la verificación de nulidad
            Booking booking = new Booking();
            // Configura la conversión de BookingData a Booking según tus necesidades
            return booking;
        });

        // Act
        Flowable<Booking> result = bookingService.getHistoryPerRoom(roomId);

        // Assert
        result.test()
                .assertComplete()
                .assertValueCount(bookingDataList.size());

        // Verifica que se llamó a findAllByRoomId con el roomId correcto
        verify(bookingRepository, times(1)).findAllByRoomId(roomId);

        // Verifica que se llamó a mapper.toEntity para cada BookingData en la lista
        verify(mapper, times(bookingDataList.size())).toEntity(any(BookingData.class));
    }

}