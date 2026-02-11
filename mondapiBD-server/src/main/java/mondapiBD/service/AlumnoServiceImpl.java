package mondapiBD.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mondapiBD.exception.NotFoundException;
import mondapiBD.model.Alumno;
import mondapiBD.model.Empresa;
import mondapiBD.model.Fecha;
import mondapiBD.model.RegistroPractica;
import mondapiBD.model.TutorDocente;
import mondapiBD.repository.AlumnoRepository;
import mondapiBD.repository.EmpresaRepository;
import mondapiBD.repository.FechaRepository;
import mondapiBD.repository.RegistroPracticaRepository;
import mondapiBD.repository.TutorDocenteRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private TutorDocenteRepository tutorDocenteRepository;

	@Autowired
	private RegistroPracticaRepository registroRepository;

	@Autowired
	private FechaRepository fechaRepository;

	@Override
	public Map<String, Object> obtenerPerfilCompleto(String alumnoId) throws NotFoundException {
		// 1. Buscamos el alumno
		Alumno alumno = alumnoRepository.findById(alumnoId)
				.orElseThrow(() -> new NotFoundException("Alumno no encontrado"));

		// 2. Buscamos la empresa vinculada por su ID
		Empresa empresa = empresaRepository.findById(alumno.getIdEmpresa()).orElse(null);

		// 3. Aplicamos el filtro de PRIVACIDAD requerido
		if (empresa != null) {
			empresa.setTutorLaboralEmail(null);
			empresa.setTutorLaboralTelefono(null);
		}

		// 4. Buscamos el tutor docente vinculado por su ID
		TutorDocente tutor = tutorDocenteRepository.findById(alumno.getIdTutorDocente()).orElse(null);

		// 5. Construimos la respuesta combinada
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("alumno", alumno);
		respuesta.put("empresa", empresa);
		respuesta.put("tutorDocente", tutor);

		return respuesta;
	}

	@Override
	public Map<String, Object> obtenerResumenHoras(String alumnoId) {
		Alumno alumno = alumnoRepository.findById(alumnoId).get();

		// Obtenemos todas las fechas generadas para su curso y evaluación
		List<Fecha> fechasPlanificadas = fechaRepository.findByAñoCursoAndEvaluacion(alumno.getAñoCurso(),
				alumno.getEvaluacion());

		// Calculamos horas realizadas sumando los registros del alumno
		List<RegistroPractica> registros = registroRepository.findByIdAlumno(alumnoId);
		Double horasRealizadas = registros.stream().mapToDouble(RegistroPractica::getHoras).sum();

		// Asumiendo jornada de 8h por fecha generada para el total requerido
		Double horasTotalesRequeridas = fechasPlanificadas.size() * 8.0;

		Map<String, Object> resumen = new HashMap<>();
		resumen.put("horasTotales", horasTotalesRequeridas);
		resumen.put("horasRealizadas", horasRealizadas);
		resumen.put("horasPendientes", horasTotalesRequeridas - horasRealizadas);
		resumen.put("porcentaje", (horasRealizadas / horasTotalesRequeridas) * 100);

		return resumen;
	}

	@Override
	public List<Alumno> listarAlumnos() {
		return alumnoRepository.findAll();
	}

	@Override
	public Alumno guardarAlumno(Alumno a) {
		return alumnoRepository.save(a);
	}
}
