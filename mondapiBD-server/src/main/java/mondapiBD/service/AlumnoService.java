package mondapiBD.service;

import java.util.List;
import java.util.Map;

import mondapiBD.exception.NotFoundException;
import mondapiBD.model.Alumno;

public interface AlumnoService {
    public Alumno guardarAlumno(Alumno alumno);
    public List<Alumno> listarAlumnos();

    /**
     * Devuelve datos del alumno sin teléfono y email del tutor laboral.
     * @throws NotFoundException 
     */
    public Map<String, Object> obtenerPerfilCompleto(String alumnoId) throws NotFoundException;

    /**
     * Calcula horas totales, realizadas, % y pendientes.
     */
    public Map<String, Object> obtenerResumenHoras(String alumnoId);
}
