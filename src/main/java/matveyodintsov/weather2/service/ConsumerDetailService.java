package matveyodintsov.weather2.service;

import lombok.RequiredArgsConstructor;
import matveyodintsov.weather2.dto.ConsumerDto;
import matveyodintsov.weather2.exception.RegistrationException;
import matveyodintsov.weather2.exception.LoginException;
import matveyodintsov.weather2.mapper.ConsumerMapper;
import matveyodintsov.weather2.model.Consumer;
import matveyodintsov.weather2.repo.ConsumerRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsumerDetailService implements UserDetailsService {

    private final ConsumerRepo repo;
    private final ConsumerMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Consumer consumer = repo.findByName(username).orElseThrow(() -> new UsernameNotFoundException(username));
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

}
