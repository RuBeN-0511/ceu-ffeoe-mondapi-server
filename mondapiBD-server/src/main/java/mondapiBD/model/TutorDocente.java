package mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tutores")
public class TutorDocente {
    @Id
    private String id;
    private String nombreCompleto;
    private Boolean activo;

}
