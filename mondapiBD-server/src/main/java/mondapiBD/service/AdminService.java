package mondapiBD.service;

import java.util.List;

import mondapiBD.exception.ConflictException;
import mondapiBD.model.Empresa;
import mondapiBD.model.TutorDocente;
import mondapiBD.model.Usuario;

public interface AdminService {
	
    // Gestión de Usuarios
    public Usuario crearUsuario(Usuario usuario) throws ConflictException; 
    public List<Usuario> listarUsuarios();
    public void alternarEstadoUsuario(String usuarioId, boolean activo);

    // Gestión de Empresas
    public Empresa guardarEmpresa(Empresa empresa);
    public List<Empresa> listarEmpresas();

    // Gestión de Tutores Docentes
    public TutorDocente guardarTutor(TutorDocente tutor);
    public List<TutorDocente> listarTutores();
}
