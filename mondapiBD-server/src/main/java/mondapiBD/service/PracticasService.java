package mondapiBD.service;

import java.time.LocalDate;
import java.util.List;

import mondapiBD.exception.ConflictException;
import mondapiBD.exception.NotFoundException;
import mondapiBD.exception.NotValidException;
import mondapiBD.model.Fecha;
import mondapiBD.model.RegistroPractica;
import mondapiBD.model.enums.Evaluacion;

public interface PracticasService {
	// Gestión de Calendario
	/**
	 * Registra fechas laborables excluyendo sábados y domingos. Lanza error si ya
	 * existen fechas para ese curso/evaluación.
	 * 
	 * @throws ConflictException
	 */
	public void generarCalendario(LocalDate inicio, LocalDate fin, Integer año, Evaluacion eval)
			throws ConflictException;

	public List<Fecha> listarFechas();

	/**
	 * Si se borra una fecha, se borran sus registros asociados.
	 */
	public void eliminarFecha(String fechaId);

	// Diario de Prácticas
	/**
	 * Valida: solo un registro por alumno/fecha. Horas entre 0 y 8 con saltos de
	 * 0.5.
	 * 
	 * @throws ConflictException
	 * @throws NotValidException
	 */
	public RegistroPractica crearRegistro(RegistroPractica registro) throws ConflictException, NotValidException;

	public void eliminarRegistro(String registroId);

	public List<RegistroPractica> listarRegistrosPorAlumno(String alumnoId);


	 public Fecha buscarByFecha(LocalDate fecha) throws NotFoundException;
}
