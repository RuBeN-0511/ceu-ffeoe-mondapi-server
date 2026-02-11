package mondapiBD.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mondapiBD.dto.request.UsuarioRequest;
import mondapiBD.dto.response.EmpresaResponse;
import mondapiBD.dto.response.UsuarioResponse;
import mondapiBD.exception.ConflictException;
import mondapiBD.model.Empresa;
import mondapiBD.model.Usuario;
import mondapiBD.model.enums.Evaluacion;
import mondapiBD.service.AdminService;
import mondapiBD.service.PracticasService;

@RestController
@RequestMapping("/mondapi/admin")
@SecurityRequirement(name = "Authorization")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PracticasService practicasService;

	@Operation(summary = "Listar empresas", description = "Devuelve empresas ordenadas alfabéticamente")
	@GetMapping("/empresas")
	public List<EmpresaResponse> listarEmpresas() {
		List<Empresa> empresas = adminService.listarEmpresas();
		return empresas.stream().map(e -> new ModelMapper().map(e, EmpresaResponse.class)).toList();
	}

	@Operation(summary = "Generar calendario de prácticas", description = "Registra días laborables excluyendo fines de semana")
	@PostMapping("/calendario/generar")
	public void generarCalendario(
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate inicio,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fin, @RequestParam Integer año,
			@RequestParam Evaluacion eval) throws ConflictException {
		practicasService.generarCalendario(inicio, fin, año, eval);
	}

	@Operation(summary = "Crear nuevo usuario", description = "Crea un acceso asociado a alumno o tutor")
	@PostMapping("/usuarios")
	public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest usuarioDto) throws ConflictException {
		Usuario nuevo = adminService.crearUsuario(new ModelMapper().map(usuarioDto, Usuario.class));
		return new ModelMapper().map(nuevo, UsuarioResponse.class);
	}
}
