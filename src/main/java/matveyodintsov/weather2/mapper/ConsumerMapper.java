package matveyodintsov.weather2.mapper;

import matveyodintsov.weather2.dto.ConsumerDto;
import matveyodintsov.weather2.model.Consumer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ConsumerMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "repeatPassword", ignore = true)
    public abstract ConsumerDto toConsumerDto(Consumer consumer);

    @Mapping(target = "password", expression = "java(encodePassword(consumerDto))")
    public abstract Consumer toConsumer(ConsumerDto consumerDto);

    protected String encodePassword(ConsumerDto consumerDto) {
        return consumerDto.getPassword() != null ?
                passwordEncoder.encode(consumerDto.getPassword()) :
                null;
    }
}


