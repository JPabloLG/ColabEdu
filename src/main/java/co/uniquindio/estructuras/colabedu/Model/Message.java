package co.uniquindio.estructuras.colabedu.Model;

public class Message {

    private String title;
    private String contentMessage;
    private User user;

    public Message(String title, String contentMessage, User user) {
        this.title = title;
        this.contentMessage = contentMessage;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message() {
    }
}
