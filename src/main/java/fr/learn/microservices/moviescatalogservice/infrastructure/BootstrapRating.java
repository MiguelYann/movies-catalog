package fr.learn.microservices.moviescatalogservice.infrastructure;

import fr.learn.microservices.moviescatalogservice.infrastructure.models.Rating;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootstrapRating {

    public static List<Rating> ratingList;

    static {
        ratingList = List.of(
                new Rating("1234", 3),
                new Rating("1235", 9)
        );
    }
}
