package matveyodintsov.weather2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    private Long id;
    private LocationDto location;
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
