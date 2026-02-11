package mondapiBD.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpresaRequest {
    @NotBlank
    private String nombre;
    @NotBlank
    private String tutorLaboralNombre;
    private String tutorLaboralEmail;
    private String tutorLaboralTelefono;
    private Boolean activo;
}
