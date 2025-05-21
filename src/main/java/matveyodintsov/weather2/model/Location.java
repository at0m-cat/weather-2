package matveyodintsov.weather2.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(exclude = "consumers")
@ToString(exclude = "consumers")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String country;

    private String state;

    private double lat;

    private double lon;

    @ManyToMany(mappedBy = "locations")
    private Set<Consumer> consumers;
}
