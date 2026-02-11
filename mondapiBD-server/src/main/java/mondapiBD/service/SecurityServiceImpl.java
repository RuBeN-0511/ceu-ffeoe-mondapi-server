package mondapiBD.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mondapiBD.exception.InactiveUserException;
import mondapiBD.exception.IncorrectPasswordException;
import mondapiBD.exception.NotFoundException;
import mondapiBD.model.Usuario;
import mondapiBD.repository.UsuarioRepository;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario login(String username, String password)
			throws NotFoundException, InactiveUserException, IncorrectPasswordException {
		// Buscamos el usuario y verificamos si está activo
		Usuario user = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

		if (!user.getActivo()) {
			throw new InactiveUserException("El usuario no está activo");
		}

		if (!user.getPassword().equals(DigestUtils.sha3_256Hex(password))) {
			throw new IncorrectPasswordException("Contraseña incorrecta");
		}
		return user;
	}

	@Override
	public void actualizarPassword(String usuarioId, String nuevaPassword) throws NotFoundException {

		Usuario user = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

		user.setPassword(DigestUtils.sha3_256Hex(nuevaPassword));
		usuarioRepository.save(user);
	}
}
