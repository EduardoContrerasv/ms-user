package cl.duoc.ms_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserResponseDto {

    @NotNull
    Long id;

    @NotBlank
    @Email
    String email;

    LocalDateTime registerDate;

    @NotNull
    int accountLevel;

}
