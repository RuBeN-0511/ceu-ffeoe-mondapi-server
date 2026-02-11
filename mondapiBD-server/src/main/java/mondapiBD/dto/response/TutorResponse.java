package mondapiBD.dto.response;

import lombok.Data;

@Data
public class TutorResponse {
	
    private String id;
    private String nombreCompleto;
    private Boolean activo;
}
