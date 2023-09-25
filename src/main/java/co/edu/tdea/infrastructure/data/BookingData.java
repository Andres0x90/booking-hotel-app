package co.edu.tdea.infrastructure.data;

import co.edu.tdea.domain.models.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "booking")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingData {
    @Id
    private String code;
    private Date startDate;
    private Date endDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "document")
    private ClientData client;
    private BigDecimal fee;
}
