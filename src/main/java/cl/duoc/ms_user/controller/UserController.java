package cl.duoc.ms_user.controller;

import cl.duoc.ms_user.dto.UserRequestDto;
import cl.duoc.ms_user.dto.UserResponseDto;
import cl.duoc.ms_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired

    final private UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<UserResponseDto>> findByStatus(@PathVariable String status) {
        List<UserResponseDto> users = service.findByAccountStatus(status);
        return ResponseEntity.ok(users);

    }

    @PutMapping("/email/{id}")
    public ResponseEntity<UserResponseDto> updateEmail(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        UserResponseDto user = service.updateEmail(id, dto.getEmail());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        UserResponseDto user = service.updatePassword(id, dto.getPassword());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<UserResponseDto> updateStatus(@PathVariable Long id, @PathVariable String status) {
        UserResponseDto user = service.updateAccountStatus(id, status);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
