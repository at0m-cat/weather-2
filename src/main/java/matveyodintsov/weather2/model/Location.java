package matveyodintsov.weather2.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@EqualsAndHashCode
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
