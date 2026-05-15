package cl.duoc.ms_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserRequestDto {
    @NotEmpty(message = "Debe ingresar un correo")
    @Email(message = "Debe ingresar un formato de email válido")
    String email;

    @NotEmpty(message = "Debe ingresar una contraseña")
    String password;

}
