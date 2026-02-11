package mondapiBD.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mondapiBD.model.enums.Ciclo;
import mondapiBD.model.enums.Evaluacion;

@Data
public class AlumnoRequest {
    @NotBlank
    private String nombreCompleto;
    private Ciclo ciclo;
    private Evaluacion evaluacion;
    private Integer añoCurso;
    private String idTutorDocente;
    private String idEmpresa;
}
