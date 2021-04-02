package fr.learn.microservices.moviescatalogservice.infrastructure;

public class Movie {

    private String name;
    private String description;

    public Movie() {

    }

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
