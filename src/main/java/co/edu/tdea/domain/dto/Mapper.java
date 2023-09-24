package co.edu.tdea.domain.dto;

import co.edu.tdea.domain.models.Booking;
import co.edu.tdea.domain.models.Client;
import co.edu.tdea.domain.models.Fine;
import co.edu.tdea.infrastructure.data.BookingData;
import co.edu.tdea.infrastructure.data.ClientData;
import co.edu.tdea.infrastructure.data.FineData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    public BookingData toData(Booking booking){
        return BookingData.builder()
                .code(booking.getCode())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .client(toData(booking.getClient()))
                .room(booking.getRoom())
                .fee(booking.getFee())
                .build();
    }
    public Booking toEntity(BookingData bookingData){
        return Booking.builder()
                .code(bookingData.getCode())
                .startDate(bookingData.getStartDate())
                .endDate(bookingData.getEndDate())
                .client(toEntity(bookingData.getClient()))
                .room(bookingData.getRoom())
                .fee(bookingData.getFee())
                .build();
    }
    public ClientData toData(Client client){
        return ClientData.builder()
                .document(client.getDocument())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .fines(toData(client.getFines()))
                .build();
    }
    public Client toEntity(ClientData clientData){
        return Client.builder()
                .document(clientData.getDocument())
                .firstName(clientData.getFirstName())
                .lastName(clientData.getLastName())
                .fines(toEntity(clientData.getFines()))
                .build();
    }

    public List<FineData> toData(List<Fine> fines){
        return fines.stream()
                .map(fine -> FineData.builder()
                        .code(fine.getCode())
                        .fee(fine.getFee())
                        .build())
                .collect(Collectors.toList());
    }

    public List<Fine> toEntity(List<FineData> fines){
        return fines.stream()
                .map(fineData -> Fine.builder()
                        .code(fineData.getCode())
                        .fee(fineData.getFee())
                        .build())
                .collect(Collectors.toList());
    }
}
