package mondapiBD.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.RegistroPractica;

public interface RegistroPracticaRepository extends MongoRepository<RegistroPractica, String> {
    public List<RegistroPractica> findByIdAlumno(String idAlumno);
    
    // Valida que no haya duplicados: un alumno no puede repetir registro en la misma fecha
    public boolean existsByIdAlumnoAndIdFecha(String idAlumno, String idFecha);
    
    // Para el borrado en cascada cuando se elimina una fecha
    public void deleteByIdFecha(String idFecha);
}
