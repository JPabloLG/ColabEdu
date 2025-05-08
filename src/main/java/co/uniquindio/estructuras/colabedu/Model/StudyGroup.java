package co.uniquindio.estructuras.colabedu.Model;

public class StudyGroup {

    private String nameGroup;
    private String description;

    public StudyGroup(String nameGroup, String description) {
        this.nameGroup = nameGroup;
        this.description = description;
    }

    public StudyGroup() {
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
