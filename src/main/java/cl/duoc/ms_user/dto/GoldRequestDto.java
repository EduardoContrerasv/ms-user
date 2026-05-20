package cl.duoc.ms_user.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class GoldRequestDto {

    @NotNull
    @Min(1)
    Integer amountToUpdate;
}