package co.uniquindio.estructuras.colabedu.Model;

import co.uniquindio.estructuras.colabedu.DTO.StudentDTO;

public class AcademicSocialNetwork {
    private static AcademicSocialNetwork singleton;
    private Student currentUser;

    public static AcademicSocialNetwork getSingleton() {
        if(singleton == null){
            singleton = new AcademicSocialNetwork();
        }
        return singleton;
    }

    public Student getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Student currentUser) {
        this.currentUser = currentUser;
    }

    public static void setSingleton(AcademicSocialNetwork singleton) {
        AcademicSocialNetwork.singleton = singleton;
    }
}