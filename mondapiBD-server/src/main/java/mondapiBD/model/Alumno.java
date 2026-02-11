package mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import mondapiBD.model.enums.Ciclo;
import mondapiBD.model.enums.Evaluacion;

@Data
@Document(collection = "alumnos")
public class Alumno {
    @Id
    private String id;
    private String nombreCompleto;
    private Ciclo ciclo;
    private Evaluacion evaluacion;
    private Integer añoCurso;
    private String idTutorDocente;
    private String idEmpresa;

}
