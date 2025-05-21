package matveyodintsov.weather2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsumerDto {
    private String name;
    private String password;
    private String repeatPassword;

}
