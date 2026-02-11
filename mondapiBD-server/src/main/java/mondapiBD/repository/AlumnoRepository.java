package mondapiBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.Alumno;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {
}
