package co.edu.tdea.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private String code;
    private Date startDate;
    private Date endDate;
    private Room room;
    private Client client;
    private BigDecimal fee;

    public void calculateFee(){
        long days = ChronoUnit.DAYS.between(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        Map<Types, BigDecimal> feePerRooms = new HashMap<>();
        feePerRooms.put(Types.SUITE, BigDecimal.valueOf(30));
        feePerRooms.put(Types.EXECUTIVE, BigDecimal.valueOf(20));
        feePerRooms.put(Types.SIMPLE, BigDecimal.valueOf(10));

        this.fee = feePerRooms.get(this.room.getType())
                .multiply(BigDecimal.valueOf(days));

        if (days > 5){
            BigDecimal iva = this.fee.multiply(BigDecimal.valueOf(0.19));
            BigDecimal descuentoIva = iva.subtract(iva.multiply(BigDecimal.valueOf(0.1)));
            this.fee = this.fee.subtract(iva).add(descuentoIva);
        }
    }
}
