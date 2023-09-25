package co.edu.tdea.domain.dto;

import co.edu.tdea.domain.models.Types;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private int id;
    private String type;
}
