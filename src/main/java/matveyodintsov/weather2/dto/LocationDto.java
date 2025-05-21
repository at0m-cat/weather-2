package matveyodintsov.weather2.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private String name;
    private String country;
    private String state;
    private double lat;
    private double lon;
}