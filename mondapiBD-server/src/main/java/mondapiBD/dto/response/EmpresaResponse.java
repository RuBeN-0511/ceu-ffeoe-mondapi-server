package mondapiBD.dto.response;

import lombok.Data;

@Data
public class EmpresaResponse {
    private String id;
    private String nombre;
    private String tutorLaboralNombre;
    private String tutorLaboralEmail;
    private String tutorLaboralTelefono;
    private Boolean activo;
}
