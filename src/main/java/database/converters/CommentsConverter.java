package database.converters;

import model.common.Comments;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter(autoApply = false)
public class CommentsConverter implements AttributeConverter<Comments, String>{
    @Override
    public String convertToDatabaseColumn(Comments comments) {
        List<String> commentsList = comments.getComments();

        StringBuilder sb = new StringBuilder("");

        commentsList.stream()
                    .limit(commentsList.size() - 2)
                    .forEach(comment -> sb.append(comment).append(", "));

        sb.append(commentsList.get(commentsList.size() - 1));

        return sb.toString();
    }

    @Override
    public Comments convertToEntityAttribute(String stringOfComments) {
        String[] arrayOfComments = stringOfComments.split(", ");

        Comments comments = new Comments();

        Arrays.stream(arrayOfComments).forEach(comments::addComment);

        return comments;
    }
}
