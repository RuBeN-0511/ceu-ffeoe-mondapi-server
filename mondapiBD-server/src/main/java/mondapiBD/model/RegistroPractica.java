package mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "registros_practicas")
public class RegistroPractica {
    @Id
    private String id;
    private String idAlumno;
    private String idFecha;
    private Double horas;
    private String descripcion;

}
