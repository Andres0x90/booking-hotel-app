package co.edu.tdea.domain.dto;

import co.edu.tdea.domain.models.Types;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO {
    private int id;
    private String type;
}
