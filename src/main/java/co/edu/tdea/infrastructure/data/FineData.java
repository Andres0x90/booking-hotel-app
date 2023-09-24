package co.edu.tdea.infrastructure.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "fine")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineData {
    @Id
    private String code;
    private BigDecimal fee;
}
