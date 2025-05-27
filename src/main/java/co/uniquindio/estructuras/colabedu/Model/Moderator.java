package co.uniquindio.estructuras.colabedu.Model;

import java.util.*;
import java.util.stream.Collectors;

public class Moderator extends User implements UserManagement, Report {

    private List<User> users;
    private List<Content> contents;

    public Moderator(String name, String email, String id, String password,
                     List<User> users, List<Content> contents) {
        super(name, email, id, password);
        this.users = users;
        this.contents = contents;
    }

    @Override
    public void updateContent(Content updatedContent) {
        for (Content c : contents) {
            if (c.getName().equals(updatedContent.getName()) && c.getPublicationDate().equals(updatedContent.getPublicationDate())) {
                c.setDescription(updatedContent.getDescription());
                c.setTypeContent(updatedContent.getTypeContent());
                c.setSubject(updatedContent.getSubject());
                System.out.println("Contenido actualizado: " + c.getName());
                return;
            }
        }
        System.out.println("Contenido no encontrado para actualizar.");// para mirar si sirve luego
    }

    @Override
    public void deleteContent(Content content) {
        if (contents.removeIf(c -> c.getName().equals(content.getName()) && c.getPublicationDate().equals(content.getPublicationDate()))) {
            System.out.println("Contenido eliminado: " + content.getName());
        } else {
            System.out.println("No se encontró el contenido para eliminar.");// para mirar si sirve luego
        }
    }

    @Override
    public void publishContent(Content content) {
        contents.add(content);
        System.out.println("Contenido publicado por " + content.getTheUser().getName() + ": " + content.getName());
    }

    @Override
    public void generateTopRatedContests() {
        // Suponiendo que cada Rating tiene un método getScore() que retorna una calificación numérica
        List<Content> topRated = contents.stream()
                .filter(c -> c.getTheRating() != null && !c.getTheRating().isEmpty())
                .sorted((c1, c2) -> Double.compare(
                        averageRating(c2.getTheRating()),
                        averageRating(c1.getTheRating())
                ))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top 5 contenidos mejor calificados:");// para mirar si sirve luego
        topRated.forEach(c -> System.out.println(c.getName() + " - Promedio: " + averageRating(c.getTheRating())));
    }


    @Override
    public void generateMostConnectedStudents() {
        // Si tienes conexiones, este método debería usar una estructura tipo grafo
        System.out.println("Este método requiere una estructura de conexiones entre estudiantes (no disponible aún).");
    }

    @Override
    public void generateShorterConnections() {
        // Igual que el anterior: se necesita una estructura de conexiones (grafo)
        System.out.println("Este método requiere un grafo para calcular rutas más cortas entre estudiantes.");
    }

    @Override
    public void generateStudyGroups() {
        // Suponiendo que los estudiantes tienen intereses
        System.out.println("Este método requiere acceso a intereses de estudiantes (no disponible en User aún).");
    }

    @Override
    public void generateLevelsParticipationStudent() {
        Map<User, Long> participation = contents.stream()
                .collect(Collectors.groupingBy(Content::getTheUser, Collectors.counting()));

        System.out.println("Niveles de participación de estudiantes:");
        participation.forEach((user, count) -> {
            String level = count >= 10 ? "Alta" : count >= 5 ? "Media" : "Baja";
            System.out.println(user.getName() + " - " + count + " contenidos - Participación " + level);
        });
    }

    @Override
    public void deleteUser(User user) {
        if (users.removeIf(u -> u.getId().equals(user.getId()))) {
            System.out.println("Usuario eliminado: " + user.getName());
        } else {
            System.out.println("No se encontró el usuario para eliminar.");
        }
    }

    @Override
    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                System.out.println("Usuario actualizado: " + updatedUser.getName());
                return;
            }
        }
        System.out.println("Usuario no encontrado para actualizar.");
    }

    @Override
    public void createUser(String name, String id, String email, String password) {
        User newUser = new Moderator(name, email, id, password, users, contents);
        users.add(newUser);
        System.out.println("Usuario creado: " + name);
    }

}
