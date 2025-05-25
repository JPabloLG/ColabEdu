package co.uniquindio.estructuras.colabedu.DAO;

import co.uniquindio.estructuras.colabedu.Model.Content;
import co.uniquindio.estructuras.colabedu.Controller.PrincipalController;
import java.util.List;

public class ContenidoDAOTemporal implements ContenidoDAO {

    @Override
    public List<Content> obtenerContenidosPorUsuario(String usuarioId) {
        return PrincipalController.contenidosTemporales;
    }

    @Override
    public boolean guardarContenido(Content contenido) {
        return PrincipalController.contenidosTemporales.add(contenido);
    }

    @Override
    public boolean actualizarContenido(Content contenido) {
        int index = PrincipalController.contenidosTemporales.indexOf(contenido);
        if (index >= 0) {
            PrincipalController.contenidosTemporales.set(index, contenido);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarContenido(String contenidoId) {
        return PrincipalController.contenidosTemporales.removeIf(c -> c.getName().equals(contenidoId));
    }

    @Override
    public List<Content> obtenerTodosContenidos() {
        return PrincipalController.contenidosTemporales;
    }
}