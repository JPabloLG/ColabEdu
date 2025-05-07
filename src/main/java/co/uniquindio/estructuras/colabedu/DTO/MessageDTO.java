package co.uniquindio.estructuras.colabedu.DTO;

import java.time.LocalDateTime;

public class MessageDTO {

    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private LocalDateTime sentAt;

    public MessageDTO(String content, LocalDateTime sentAt, int id, int receiverId, int senderId) {
        this.content = content;
        this.sentAt = sentAt;
        this.id = id;
        this.receiverId = receiverId;
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", sentAt=" + sentAt +
                '}';
    }
}
