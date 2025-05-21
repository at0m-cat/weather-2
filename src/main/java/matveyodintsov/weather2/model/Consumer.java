package matveyodintsov.weather2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "consumers")
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(exclude = "locations")
@ToString(exclude = "locations")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "consumer_locations",
            joinColumns = @JoinColumn(name = "consumers_id"),
            inverseJoinColumns = @JoinColumn(name = "locations_id")
    )
    private Set<Location> locations;
}
