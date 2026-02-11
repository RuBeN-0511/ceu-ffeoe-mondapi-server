package mondapiBD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mondapiBD.exception.ConflictException;
import mondapiBD.model.Alumno;
import mondapiBD.model.Empresa;
import mondapiBD.model.TutorDocente;
import mondapiBD.model.Usuario;
import mondapiBD.model.enums.Perfil;
import mondapiBD.repository.AlumnoRepository;
import mondapiBD.repository.EmpresaRepository;
import mondapiBD.repository.TutorDocenteRepository;
import mondapiBD.repository.UsuarioRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private TutorDocenteRepository tutorRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario, String nombreCompletoAsociado) throws ConflictException {
		// 1. El nombre de usuario tiene que ser único
		if (usuarioRepository.existsByUsername(usuario.getUsername())) {
			throw new ConflictException("El nombre de usuario ya existe");
		}

		// 2. Comprobar perfil y crear la entidad asociada correspondiente
		if (usuario.getPerfil() == Perfil.ALUMNO) {
			
			Alumno nuevoAlumno = new Alumno();
			nuevoAlumno.setNombreCompleto(nombreCompletoAsociado);
			// El resto de campos (ciclo, empresa...) se editarán a posteriori por el administrador
			alumnoRepository.save(nuevoAlumno);
			
			usuario.setIdAsociado(nuevoAlumno.getId());

		} else if (usuario.getPerfil() == Perfil.TUTOR) {
			
			TutorDocente nuevoTutor = new TutorDocente();
			nuevoTutor.setNombreCompleto(nombreCompletoAsociado);
			nuevoTutor.setActivo(true);
			tutorRepository.save(nuevoTutor);

			usuario.setIdAsociado(nuevoTutor.getId());
		}

		// 3. El usuario se guarda como activo por defecto
		usuario.setActivo(true);

		// 4. Guardamos el usuario con su vínculo ya establecido
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public void alternarEstadoUsuario(String usuarioId, boolean activo) {
		Usuario user = usuarioRepository.findById(usuarioId).get();
		user.setActivo(activo);
		usuarioRepository.save(user);
	}

	@Override
	public List<Empresa> listarEmpresas() {
		// Ordenado alfabéticamente por nombre de empresa
		return empresaRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

	@Override
	public List<TutorDocente> listarTutores() {
		// Ordenado alfabéticamente por nombre del tutor
		return tutorRepository.findAll(Sort.by(Sort.Direction.ASC, "nombreCompleto"));
	}

	public Empresa guardarEmpresa(Empresa e) {
		return empresaRepository.save(e);
	}

	public TutorDocente guardarTutor(TutorDocente t) {
		return tutorRepository.save(t);
	}
}
