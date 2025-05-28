package co.uniquindio.estructuras.colabedu.Model;

import java.util.ArrayList;
import java.util.List;

public class Rating {
    private List<Integer> ratings;
    private String comment;

    public List<Integer> getRatings() {
        return ratings;
    }

    public Rating() {
        this.ratings = new ArrayList<>();
    }

    public void addRating(int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
        this.ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return (double) sum / ratings.size();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "averageRating=" + getAverageRating() +
                ", comment='" + comment + '\'' +
                '}';
    }
}