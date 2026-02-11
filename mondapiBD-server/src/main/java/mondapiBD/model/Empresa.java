package mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "empresas")
public class Empresa {
    @Id
    private String id;
    private String nombre;
    private String tutorLaboralNombre;
    private String tutorLaboralEmail;
    private String tutorLaboralTelefono;
    private Boolean activo;

}
