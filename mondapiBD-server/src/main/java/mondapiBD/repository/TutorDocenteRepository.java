package mondapiBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.TutorDocente;

public interface TutorDocenteRepository extends MongoRepository<TutorDocente, String> {
}
