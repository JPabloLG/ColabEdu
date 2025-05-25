package co.uniquindio.estructuras.colabedu.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Content {
    // Atributos
    private String name;
    private LocalDateTime publicationDate;
    private String typeContent;
    private String description;
    private String subject;
    private User theUser;
    private ArrayList<Rating> theRating;
    private byte[] fileData;
    private String fileName;
    private String fileType;

    // Constructor
    public Content(String name, LocalDateTime publicationDate, String typeContent,
                   String description, String subject, User theUser,
                   ArrayList<Rating> theRating, byte[] fileData, String fileName, String fileType){
        this.name = name;
        this.publicationDate = publicationDate;
        this.typeContent = typeContent;
        this.description = description;
        this.subject = subject;
        this.theUser = theUser;
        this.fileData = fileData;
        this.fileName = fileName;
        this.fileType = fileType;
        this.theRating = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getTheUser() {
        return theUser;
    }

    public void setTheUser(User theUser) {
        this.theUser = theUser;
    }

    public ArrayList<Rating> getTheRating() {
        return theRating;
    }

    public void setTheRating(ArrayList<Rating> theRating) {
        this.theRating = theRating;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    // Método para obtener la extensión del archivo
    public String getFileExtension() {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    @Override
    public String toString() {
        return "Content{" +
                "name='" + name + '\'' +
                ", publicationDate=" + publicationDate +
                ", typeContent='" + typeContent + '\'' +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                ", theUser=" + theUser +
                ", theRating=" + theRating +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileDataSize=" + (fileData != null ? fileData.length + " bytes" : "null") +
                '}';
    }
}