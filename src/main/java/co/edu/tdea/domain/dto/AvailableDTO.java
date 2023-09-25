package co.edu.tdea.domain.dto;

import co.edu.tdea.domain.models.Types;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AvailableDTO {
    private String type;
    private Date initDate;
    private Date endDate;
}
