package cl.duoc.ms_user.service.UserServiceImpl;

import cl.duoc.ms_user.dto.UserRequestDto;
import cl.duoc.ms_user.dto.UserResponseDto;
import cl.duoc.ms_user.model.User;
import cl.duoc.ms_user.repository.UserRepository;
import cl.duoc.ms_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getRegisterDate(),
                user.getAccountLevel()
        );
    }

    private User toEntity(UserRequestDto dto){
        return new User(null, dto.getEmail(), dto.getPassword(), LocalDateTime.now(),"Active",1);
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

    @Override
    public List<UserResponseDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<UserResponseDto> findByAccountStatus(String accountStatus) {
        List<UserResponseDto> users = repository.findByAccountStatus(accountStatus)
                .stream()
                .map(this::toDto)
                .toList();

        if (users.isEmpty()) {
            throw new RuntimeException("No hay cuentas con el estado '" + accountStatus + "'");
        }

        return users;

    }

    public UserResponseDto findByEmail(String email) {
        return repository.findByEmail(email).map(this::toDto).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UserResponseDto updateEmail(Long id, String newEmail) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setEmail(newEmail);
        return toDto(repository.save(user));
    }

    @Override
    public UserResponseDto updatePassword(Long id, String newPassword) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setPassword(newPassword);
        return toDto(repository.save(user));
    }


    @Override
    public UserResponseDto updateAccountStatus(Long id, String newStatus) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setAccountStatus(newStatus);
        return toDto(repository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuario con ID " + id + " no encontrado");
        }
        repository.deleteById(id);
    }
}
