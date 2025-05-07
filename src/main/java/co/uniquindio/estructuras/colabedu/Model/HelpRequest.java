package co.uniquindio.estructuras.colabedu.Model;

public class HelpRequest implements Comparable<HelpRequest> {
    private String title;
    private String subject;
    private String description;
    private int priorityLevel;

    public HelpRequest(String title, String subject, String description, int priorityLevel) {
        this.title = title;
        this.subject = subject;
        this.description = description;
        this.priorityLevel = priorityLevel;
    }

    public HelpRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @Override
    public int compareTo(HelpRequest o) {
        return this.priorityLevel-o.getPriorityLevel();
    }
}
