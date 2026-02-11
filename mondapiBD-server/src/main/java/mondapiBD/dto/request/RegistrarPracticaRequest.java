package mondapiBD.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RegistrarPracticaRequest {
    @NotBlank
    private LocalDate fecha;
    
    @Positive(message = "Las horas deben ser mayores a 0")
    @Max(value = 8, message = "No se pueden registrar más de 8 horas diarias")
    private Double horas;
    
    @NotBlank
    private String descripcion;

}
