package cl.duoc.ms_user.service.UserServiceImpl;

import cl.duoc.ms_user.dto.UserRequestDto;
import cl.duoc.ms_user.dto.UserResponseDto;
import cl.duoc.ms_user.model.User;
import cl.duoc.ms_user.repository.UserRepository;
import cl.duoc.ms_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getRegisterDate()
        );
    }

    private User toEntity(UserRequestDto dto){
        return new User(null, dto.getEmail(), dto.getPassword(), LocalDateTime.now());
    }

    @Override
    public UserResponseDto register(UserRequestDto dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya existe");
        }

        User newUser = this.toEntity(dto);
        return this.toDto(repository.save(newUser));
    }

    @Override
    public UserResponseDto login(UserRequestDto dto) {
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ingresado inválido"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Contraseña ingresada inválida");
        }

        return this.toDto(user);
    }
}
