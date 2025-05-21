package matveyodintsov.weather2.service;

import lombok.RequiredArgsConstructor;
import matveyodintsov.weather2.dto.ConsumerDto;
import matveyodintsov.weather2.dto.WeatherDto;
import matveyodintsov.weather2.exception.RegistrationException;
import matveyodintsov.weather2.mapper.ConsumerMapper;
import matveyodintsov.weather2.mapper.LocationMapper;
import matveyodintsov.weather2.mapper.WeatherMapper;
import matveyodintsov.weather2.model.Consumer;
import matveyodintsov.weather2.repo.ConsumerRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsumerDetailService implements UserDetailsService {

    private final ConsumerRepo repo;
    private final ConsumerMapper mapper;
    private final WeatherMapper weatherMapper;
    private final LocationMapper locationMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Consumer consumer = repo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return User.builder()
                .username(consumer.getName())
                .password(consumer.getPassword())
                .roles("USER")
                .build();
    }

    public void registerNewConsumer(ConsumerDto consumerDto) {
        if (repo.existsByNameIgnoreCase(consumerDto.getName())) {
            throw new RegistrationException("Username already exists");
        }
        repo.save(mapper.toConsumer(consumerDto));
    }

    public String getConsumerAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public List<WeatherDto> getWeatherByAuthenticateUsername(String username) {
        return repo.findWeatherByUsername(username).stream()
                .map(weather -> {
                    WeatherDto weatherDto = weatherMapper.toWeatherDto(weather);
                    weatherDto.setLocation(locationMapper.toDto(weather.getLocation()));
                    return weatherDto;
                })
                .collect(Collectors.toList());
    }

}
