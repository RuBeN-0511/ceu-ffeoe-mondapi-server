package mondapiBD.dto.response;

import lombok.Data;

@Data
public class RegistroPracticaResponse {

    private String id;
    private String idAlumno;
    private String idFecha;
    private Double horas;
    private String descripcion;
}
