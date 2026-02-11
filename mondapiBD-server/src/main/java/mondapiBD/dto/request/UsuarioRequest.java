package mondapiBD.dto.request;

import lombok.Data;
import mondapiBD.model.enums.Perfil;

@Data
public class UsuarioRequest {

    private String username;
    private String password;
    private Perfil perfil;
    private String idAsociado;
    private Boolean activo;
}
