package matveyodintsov.weather2.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    private LocationDto location;

}
