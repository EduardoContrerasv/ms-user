package cl.duoc.ms_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserRegisterDto {

    @NotBlank(message = "Debe ingresar un email")
    @Email(message = "Debe tener formato de email válido")
    private String email;

    @NotBlank(message = "Debe ingresar una contraseña")
    @Size(min = 6, message = "La contraseña debe ser de 6 caracteres o más")
    private String password;
}
