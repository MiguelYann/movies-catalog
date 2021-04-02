package fr.learn.microservices.moviescatalogservice.domain.models;

public class MovieItem {

    private final String name;
    private final String description;
    private final int rating;

    public MovieItem(final String name, final String description, final int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }
}
