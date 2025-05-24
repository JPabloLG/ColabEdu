package co.uniquindio.estructuras.colabedu.Util;

import co.uniquindio.estructuras.colabedu.Model.Student;
import co.uniquindio.estructuras.colabedu.Model.FriendshipManager;

public class DataInitializer {


    public static void initializeSampleData() {
        // Crear estudiantes
        Student s1 = new Student("Ana López", "ana@email.com", "001", "pass123", "Instituto Central");
        Student s2 = new Student("Carlos Ruiz", "carlos@email.com", "002", "pass123", "Instituto Central");

        // Añadir al manager
        FriendshipManager manager = FriendshipManager.getInstance();
        manager.addStudent(s1);
        manager.addStudent(s2);

        // Establecer amistad
        manager.addFriendship("001", "002");

        // Verificar que la relación se creó
        System.out.println("Amigos de Ana: " + s1.getFriends().size());
        System.out.println("Amigos de Carlos: " + s2.getFriends().size());
    }


    // Métodos preparados para SQL (a implementar después)
    public static void saveStudentToSQL(Student student) {
        // TODO: Implementar conexión a base de datos
        System.out.println("SIMULACIÓN SQL: Guardando estudiante " + student.getName());
    }

    public static void saveFriendshipToSQL(String studentId1, String studentId2) {
        // TODO: Implementar conexión a base de datos
        System.out.println("SIMULACIÓN SQL: Guardando amistad entre " + studentId1 + " y " + studentId2);
    }
}