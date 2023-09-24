package co.edu.tdea.domain.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    private int id;
    private  Types type;

}
