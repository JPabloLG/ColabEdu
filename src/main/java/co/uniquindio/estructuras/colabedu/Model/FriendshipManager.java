package co.uniquindio.estructuras.colabedu.Model;

import co.uniquindio.estructuras.colabedu.Util.DataInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendshipManager {
    private static FriendshipManager instance;
    private Map<String, Student> students;
    private Map<String, List<String>> friendships;

    private FriendshipManager() {
        students = new HashMap<>();
        friendships = new HashMap<>();
    }

    public static FriendshipManager getInstance() {
        if (instance == null) {
            synchronized (FriendshipManager.class) {
                if (instance == null) {
                    instance = new FriendshipManager();
                }
            }
        }
        return instance;
    }

    public void addStudent(Student student) {
        if (student != null && student.getId() != null) {
            students.put(student.getId(), student);
            friendships.put(student.getId(), new ArrayList<>());
        }
    }

    public void addFriendship(String studentId1, String studentId2) {
        if (studentId1 != null && studentId2 != null && !studentId1.equals(studentId2)) {
            List<String> friends1 = friendships.get(studentId1);
            List<String> friends2 = friendships.get(studentId2);

            if (friends1 != null && friends2 != null && !friends1.contains(studentId2)) {
                friends1.add(studentId2);
                friends2.add(studentId1);
            }
        }
    }

    public List<Student> getFriends(Student student) {
        List<Student> friendList = new ArrayList<>();
        if (student != null && student.getId() != null) {
            List<String> friendIds = friendships.get(student.getId());
            if (friendIds != null) {
                for (String friendId : friendIds) {
                    Student friend = students.get(friendId);
                    if (friend != null) {
                        friendList.add(friend);
                    }
                }
            }
        }
        return friendList;
    }

    // Métodos para futura integración con SQL
    public void loadFriendshipsFromSQL() {
        // TODO: Implementar conexión a base de datos
    }

    public void saveFriendshipToSQL(String studentId1, String studentId2) {
        // TODO: Implementar conexión a base de datos
    }

    // En tu clase FriendshipManager, añade estos métodos:
    public void addStudentWithSQLReady(Student student) {
        addStudent(student); // Método temporal actual
        DataInitializer.saveStudentToSQL(student); // Llamada preparada para SQL
    }

    public void addFriendshipWithSQLReady(String studentId1, String studentId2) {
        addFriendship(studentId1, studentId2); // Método temporal actual
        DataInitializer.saveFriendshipToSQL(studentId1, studentId2); // Llamada preparada para SQL
    }
}