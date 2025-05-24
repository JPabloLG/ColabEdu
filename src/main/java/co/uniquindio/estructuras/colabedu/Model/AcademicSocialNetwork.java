package co.uniquindio.estructuras.colabedu.Model;

public class AcademicSocialNetwork {
    private static AcademicSocialNetwork singleton;

    public static AcademicSocialNetwork getSingleton() {
        if(singleton == null){
            singleton = new AcademicSocialNetwork();
        }
        return singleton;
    }

    //falta el constructor con sus respectivas lista propias
}
