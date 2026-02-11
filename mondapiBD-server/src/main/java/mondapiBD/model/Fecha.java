package mondapiBD.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import mondapiBD.model.enums.Evaluacion;

@Data
@Document(collection = "fechas")
public class Fecha {
    @Id
    private String id;
    private LocalDate fecha;
    private Integer añoCurso;
    private Evaluacion evaluacion;

}
