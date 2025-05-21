package matveyodintsov.weather2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weathers")
@Getter @Setter
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Location location;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;
    private Integer seaLevel;
    private Integer grndLevel;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;
    private double windSpeed;
    private int windDeg;
    private Double windGust;
    private int cloudiness;
    private Integer visibility;
    private LocalDateTime dt;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private int timezone;
}
