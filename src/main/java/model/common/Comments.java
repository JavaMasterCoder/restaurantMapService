package model.common;

import java.util.ArrayList;
import java.util.List;

public class Comments {
    private final List<String> comments;

    public Comments() {
        comments = new ArrayList<>();
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void removeComment(String comment) {
        comments.remove(comment);
    }
}
