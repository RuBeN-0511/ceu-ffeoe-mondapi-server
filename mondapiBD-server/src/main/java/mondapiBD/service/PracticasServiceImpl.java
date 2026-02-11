package mondapiBD.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import mondapiBD.exception.ConflictException;
import mondapiBD.exception.NotFoundException;
import mondapiBD.exception.NotValidException;
import mondapiBD.model.Fecha;
import mondapiBD.model.RegistroPractica;
import mondapiBD.model.enums.Evaluacion;
import mondapiBD.repository.FechaRepository;
import mondapiBD.repository.RegistroPracticaRepository;

@Service
public class PracticasServiceImpl implements PracticasService {

	@Autowired
	private FechaRepository fechaRepository;

	@Autowired
	private RegistroPracticaRepository registroRepository;

	@Autowired
	private MongoTemplate mongoTemplate; // Para borrados en cascada y queries complejas

	@Override
	public void generarCalendario(LocalDate inicio, LocalDate fin, Integer año, Evaluacion eval)
			throws ConflictException {
		// Si ya existen fechas para ese curso y evaluación, dar error
		if (fechaRepository.existsByAñoCursoAndEvaluacion(año, eval)) {
			throw new ConflictException("Ya existen fechas registradas para este periodo");
		}

		List<Fecha> fechasAGenerar = new ArrayList<>();
		LocalDate actual = inicio;
		while (!actual.isAfter(fin)) {
			// Solo días laborables (excluir sábado y domingo)
			if (actual.getDayOfWeek().getValue() < 6) {
				Fecha f = new Fecha();
				f.setFecha(actual);
				f.setAñoCurso(año);
				f.setEvaluacion(eval);
				fechasAGenerar.add(f);
			}
			actual = actual.plusDays(1);
		}
		fechaRepository.saveAll(fechasAGenerar);
	}

	@Override
	public RegistroPractica crearRegistro(RegistroPractica registro) throws ConflictException, NotValidException {
		// Validar: solo un registro por alumno y fecha
		if (registroRepository.existsByIdAlumnoAndIdFecha(registro.getIdAlumno(), registro.getIdFecha())) {
			throw new ConflictException("Ya existe un registro para esta fecha");
		}

		// Validar: horas entre 0.5 y 8, con saltos de 0.5
		Double horas = registro.getHoras();
		if (horas <= 0 || horas > 8 || (horas % 0.5 != 0)) {
			throw new NotValidException("La cantidad de horas debe ser entre 0.5 y 8, en intervalos de 0.5");
		}

		return registroRepository.save(registro);
	}

	@Override
	public void eliminarFecha(String fechaId) {
		// Borrado en cascada: eliminar registros asociados a la fecha
		Query query = new Query(Criteria.where("idFecha").is(fechaId));
		mongoTemplate.remove(query, RegistroPractica.class);

		fechaRepository.deleteById(fechaId);
	}

	@Override
	public List<RegistroPractica> listarRegistrosPorAlumno(String alumnoId) {
		return registroRepository.findByIdAlumno(alumnoId);
	}

	@Override
	public List<Fecha> listarFechas() {
		return fechaRepository.findAll();
	}

	@Override
	public void eliminarRegistro(String id) {
		registroRepository.deleteById(id);
	}

	@Override
	public Fecha buscarFecha(String id) throws NotFoundException {
		
		 return fechaRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado registros en esa fecha"));
		// TODO Auto-generated method stub
	}
}
