package database.converters;

import model.restaurants.Coordinate;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class CoordinateConverter implements AttributeConverter<Coordinate, String> {

    @Override
    public String convertToDatabaseColumn(Coordinate coordinate) {
        Double[] coordinates = coordinate.getCoordinate();

        return new StringBuilder().append(coordinates[0])
                .append(";")
                .append(coordinates[1])
                .toString();
    }

    @Override
    public Coordinate convertToEntityAttribute(String stringCoordinate) {
        if (stringCoordinate == null) {
            throw new NullPointerException("Failed to convert null coordinate");
        }
        String[] coords = stringCoordinate.split(";");

        if (coords.length != 2) {
            throw  new IllegalArgumentException("Coordinate can be contained only of two parameters");
        }

        Double Xcoordinate = Double.parseDouble(coords[0]);
        Double Ycoordinate = Double.parseDouble(coords[1]);

        return new Coordinate(new Double[] {Xcoordinate, Ycoordinate});
    }
}
