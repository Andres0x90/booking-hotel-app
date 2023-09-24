package co.edu.tdea.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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
}
