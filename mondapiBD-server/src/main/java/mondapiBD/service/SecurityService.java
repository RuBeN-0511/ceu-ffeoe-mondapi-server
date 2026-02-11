package mondapiBD.service;

import mondapiBD.exception.InactiveUserException;
import mondapiBD.exception.IncorrectPasswordException;
import mondapiBD.exception.NotFoundException;
import mondapiBD.model.Usuario;

public interface SecurityService {
    /**
     * Valida credenciales. La password debe llegar en SHA-256.
     * Solo permite el acceso si el usuario está activo.
     * @throws NotFoundException 
     * @throws InactiveUserException 
     * @throws IncorrectPasswordException 
     */
    public Usuario login(String username, String password) throws NotFoundException, InactiveUserException, IncorrectPasswordException;

    /**
     * Valida longitud mínima de 8 caracteres antes de actualizar.
     * @throws NotFoundException 
     */
    public void actualizarPassword(String usuarioId, String nuevaPassword) throws NotFoundException;
}
