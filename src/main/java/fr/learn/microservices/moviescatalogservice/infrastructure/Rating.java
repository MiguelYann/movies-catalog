package fr.learn.microservices.moviescatalogservice.infrastructure;

public class Rating {
    private final String movieId;
    private final int count;

    public Rating(String movieId, int count) {
        this.movieId = movieId;
        this.count = count;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "movieId='" + movieId + '\'' +
                ", count=" + count +
                '}';
    }
}