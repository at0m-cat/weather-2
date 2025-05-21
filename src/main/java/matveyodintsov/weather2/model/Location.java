package matveyodintsov.weather2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String country;

    private String state;

    private double lat;

    private double lon;
}
