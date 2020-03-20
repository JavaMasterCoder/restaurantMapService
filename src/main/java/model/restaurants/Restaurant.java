package model.restaurants;

import database.converters.CommentsConverter;
import database.converters.CoordinateConverter;
import model.common.Comments;
import model.users.Owner;

import javax.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Owner owner;

    @Column
    @Convert(converter = CommentsConverter.class)
    private Comments comments;

    @Column(nullable = false)
    @Convert(converter = CoordinateConverter.class)
    private Coordinate coordinate;

    @Column
    private int rating = 0;

    public Restaurant() {
    }

    public Restaurant(String name, Owner owner, Coordinate coordinate) {
        this.name = name;
        this.owner = owner;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
