package co.uniquindio.estructuras.colabedu.Model;

import co.uniquindio.estructuras.colabedu.Model.Interfaces.Report;
import co.uniquindio.estructuras.colabedu.Model.Interfaces.UserManagement;

public class Moderator extends User implements UserManagement, Report {

    public Moderator(String name, String email, String id, String password) {
        super(name, email, id, password);
    }


    @Override
    public void generateTopRatedContests() {

    }

    @Override
    public void generateMostConnectedStudents() {

    }

    @Override
    public void generateShorterConnections() {

    }

    @Override
    public void generateStudyGroups() {

    }

    @Override
    public void generateLevelsParticipationStudent() {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void createUser(String name, String id, String email, String password) {

    }
}
