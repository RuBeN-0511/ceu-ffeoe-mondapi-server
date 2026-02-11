package mondapiBD.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.Fecha;
import mondapiBD.model.enums.Evaluacion;

public interface FechaRepository extends MongoRepository<Fecha, String> {
	// Para listar fechas de un periodo concreto y evitar duplicados al generar
	public List<Fecha> findByAñoCursoAndEvaluacion(Integer añoCurso, Evaluacion evaluacion);

	public boolean existsByAñoCursoAndEvaluacion(Integer añoCurso, Evaluacion evaluacion);

	public Optional<Fecha> findByFecha(LocalDate fecha);

}
