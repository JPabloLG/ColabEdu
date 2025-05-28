package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.Model.Content;
import java.util.List;

public interface ContenidoDAO {
    List<Content> obtenerContenidosPorUsuario(String usuarioId);
    boolean guardarContenido(Content contenido);
    boolean actualizarContenido(Content contenido);
    boolean eliminarContenido(String contenidoId);
    List<Content> obtenerTodosContenidos();
}