package fr.learn.microservices.moviescatalogservice.domain.models;

public class MovieItem {
    
    private final String name;
    private final String description;
    private final String rating;

    public MovieItem(final String name, final String description, final String rating) {
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

    public String getRating() {
        return rating;
    }
}
