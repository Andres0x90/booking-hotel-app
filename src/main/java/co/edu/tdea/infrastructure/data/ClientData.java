package co.edu.tdea.infrastructure.data;

import co.edu.tdea.domain.models.Fine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "client")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {
    private String document;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<FineData> fines;
}
