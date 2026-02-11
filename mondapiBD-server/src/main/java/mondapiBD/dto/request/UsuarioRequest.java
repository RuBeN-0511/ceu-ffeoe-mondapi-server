package mondapiBD.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mondapiBD.model.enums.Perfil;

@Data
public class UsuarioRequest {

	@NotBlank(message = "El nombre de usuario es obligatorio")
	private String username;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	private String password;

	@NotNull(message = "El perfil es obligatorio")
	private Perfil perfil;
}
