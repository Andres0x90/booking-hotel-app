package co.edu.tdea.infrastructure.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "booking")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingData {
    @Id
    private String code;
    private Date startDate;
    private Date endDate;
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL)
    private ClientData client;
    private BigDecimal fee;
}
