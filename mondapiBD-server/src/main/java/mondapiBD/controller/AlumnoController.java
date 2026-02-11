package mondapiBD.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mondapiBD.dto.request.RegistrarPracticaRequest;
import mondapiBD.dto.response.AlumnoResponse;
import mondapiBD.dto.response.RegistroPracticaResponse;
import mondapiBD.exception.ConflictException;
import mondapiBD.exception.NotFoundException;
import mondapiBD.exception.NotValidException;
import mondapiBD.model.Alumno;
import mondapiBD.model.RegistroPractica;
import mondapiBD.service.AlumnoService;
import mondapiBD.service.PracticasService;

@RestController
@RequestMapping("/mondapi/alumno")
@SecurityRequirement(name = "Authorization")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private PracticasService practicasService;

	@Operation(summary = "Ver perfil del alumno", description = "Muestra datos y resumen de horas. Oculta contacto del tutor laboral")
	@GetMapping("/{id}/perfil")
	public AlumnoResponse verPerfil(@PathVariable String id) throws NotFoundException {
		Map<String, Object> perfilCompleto = alumnoService.obtenerPerfilCompleto(id);

		Alumno alumno = (Alumno) perfilCompleto.get("alumno");
		AlumnoResponse response = new ModelMapper().map(alumno, AlumnoResponse.class);

		Map<String, Object> resumen = alumnoService.obtenerResumenHoras(id);
		response.setHorasTotales((Double) resumen.get("horasTotales"));
		response.setHorasRealizadas((Double) resumen.get("horasRealizadas"));
		response.setHorasPendientes((Double) resumen.get("horasPendientes"));
		response.setPorcentajeCompletado((Double) resumen.get("porcentaje"));

		return response;
	}

	@Operation(summary = "Registrar tareas diarias", description = "Crea un registro de horas. Máximo 8h y saltos de 0.5h")
	@PostMapping("/{id}/registro")
	public RegistroPracticaResponse crearRegistro(@Valid @RequestBody RegistrarPracticaRequest dto,
			@PathVariable String alumnoId) throws ConflictException, NotValidException {
		
		RegistroPractica registro = new ModelMapper().map(dto, RegistroPractica.class);
		registro.setIdAlumno(alumnoId);
		RegistroPractica resgitroCreado = practicasService.crearRegistro(registro);
		return new ModelMapper().map(resgitroCreado, RegistroPracticaResponse.class);
	}

	@Operation(summary = "Listar mis registros", description = "Muestra todas las tareas realizadas por el alumno")
	@GetMapping("/{id}/registros")
	public List<RegistroPracticaResponse> listarRegistros(@PathVariable String id) {
		List<RegistroPractica> registros = practicasService.listarRegistrosPorAlumno(id);
		return registros.stream().map(r -> new ModelMapper().map(r, RegistroPracticaResponse.class)).toList();
	}
	
    @Operation(summary = "Borrar un registro de prácticas", description = "Permite al alumno eliminar un registro desde el detalle. Requisito [8]")
    @DeleteMapping("/registro/{id}")
    public void borrarRegistro(@PathVariable String id) {
        practicasService.eliminarRegistro(id);
    }
}
