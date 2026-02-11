package mondapiBD.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.Fecha;
import mondapiBD.model.enums.Evaluacion;

public interface FechaRepository extends MongoRepository<Fecha, String> {
    // Para listar fechas de un periodo concreto y evitar duplicados al generar
    public List<Fecha> findByAñoCursoAndEvaluacion(Integer añoCurso, Evaluacion evaluacion);
    public boolean existsByAñoCursoAndEvaluacion(Integer añoCurso, Evaluacion evaluacion);
}
