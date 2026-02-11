package mondapiBD.dto.response;

import lombok.Data;
import mondapiBD.model.enums.Ciclo;
import mondapiBD.model.enums.Evaluacion;

@Data
public class AlumnoResponse {

    private String id;
    private String nombreCompleto;
    private Ciclo ciclo;
    private Evaluacion evaluacion;
    private Integer añoCurso;
    private String idTutorDocente;
    private String idEmpresa;
}
