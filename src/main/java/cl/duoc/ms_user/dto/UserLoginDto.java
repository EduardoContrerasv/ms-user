package cl.duoc.ms_user.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserLoginDto {
    @NotBlank(message = "Debe ingresar un email")
    private String email;

    @NotBlank(message = "Debe ingresar una contraseña")
    private String password;
}
