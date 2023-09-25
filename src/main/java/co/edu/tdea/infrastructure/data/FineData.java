package co.edu.tdea.infrastructure.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "fine")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FineData {
    @Id
    private String code;
    private BigDecimal fee;

    @PrePersist
    public void prePersist(){
        this.code = UUID.randomUUID().toString();
    }
}
