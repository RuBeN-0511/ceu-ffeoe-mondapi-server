package mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import mondapiBD.model.enums.Perfil;

@Data
@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;
    private String username;
    private String password;
    private Perfil perfil;
    private String idAsociado;
    private Boolean activo;

}