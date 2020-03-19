package model.restaurants;

import database.converters.CommentsConverter;
import database.converters.CoordinateConverter;
import model.common.Comments;

import javax.persistence.*;

@Entity
@Table(name = "Restaurants")
public class Restaurant {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Convert(converter = CommentsConverter.class)
    private Comments comments;

    @Column
    @Convert(converter = CoordinateConverter.class)
    private Coordinate coordinate;

    @Column
    private int rating = 0;

    public Restaurant() {
    }

    public Restaurant(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;

        comments = new Comments();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Coordinate getCoordinates() {
        return coordinate;
    }

    public void setCoordinates(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
