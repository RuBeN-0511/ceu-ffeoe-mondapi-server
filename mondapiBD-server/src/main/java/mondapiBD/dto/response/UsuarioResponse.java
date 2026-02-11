package mondapiBD.dto.response;

import lombok.Data;
import mondapiBD.model.enums.Perfil;

@Data
public class UsuarioResponse {
    private String id;
    private String username;
    private Perfil perfil;
    private String idAsociado;
    private Boolean activo;
}