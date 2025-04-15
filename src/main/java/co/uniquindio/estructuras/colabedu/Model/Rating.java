package co.uniquindio.estructuras.colabedu.Model;

public class Rating {
    private int rating;
    private String comment;

    public Rating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
