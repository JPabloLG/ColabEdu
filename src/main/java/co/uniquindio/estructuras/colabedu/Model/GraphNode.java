package co.uniquindio.estructuras.colabedu.Model;

// Clase interna para el grafo
public class GraphNode {
    private Student currentUser;
    private String id;
    private String label;
    private double size;

    public GraphNode(String id, String label, double size) {
        this.id = id;
        this.label = label;
        this.size = size;
    }
    public String getId() { return id; }
    public String getLabel() { return label; }
    public double getSize() { return size; }

    public GraphNode toGraphNode() {
        return new GraphNode(this.getId(), currentUser.getName(), calculateNodeSize());
    }
    private double calculateNodeSize() {
        double baseSize = 30.0;
        double activityFactor = 0.5 * currentUser.getMessageLinkedList().size() +
                0.3 * currentUser.getStudyGroupLinkedList().size() +
                0.2 * currentUser.getContents().size();
        return Math.min(baseSize + activityFactor, 60.0);
    }
}