package co.edu.tdea.domain.dto;

import co.edu.tdea.domain.models.Types;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class RoomDTO {
    private int id;
    private String type;
}
