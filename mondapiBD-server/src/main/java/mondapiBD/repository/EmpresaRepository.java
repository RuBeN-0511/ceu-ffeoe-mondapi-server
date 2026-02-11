package mondapiBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mondapiBD.model.Empresa;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
}
