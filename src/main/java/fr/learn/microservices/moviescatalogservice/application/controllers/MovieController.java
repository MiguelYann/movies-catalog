package fr.learn.microservices.moviescatalogservice.application.controllers;

import fr.learn.microservices.moviescatalogservice.domain.models.MovieItem;
import fr.learn.microservices.moviescatalogservice.infrastructure.BootstrapRating;
import fr.learn.microservices.moviescatalogservice.infrastructure.Movie;
import fr.learn.microservices.moviescatalogservice.infrastructure.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/movies")
public class MovieController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{userId}")
    public List<MovieItem> getMovies(@PathVariable("userId") String userId) {

        log.info("Id of user is {}", userId);

        List<Rating> ratingList = BootstrapRating.ratingList;

        log.info("Call Service info, to retrieved information about movie with ratings {}", ratingList);

        return ratingList.stream()
                .map(rating -> {
                    Movie movie = restTemplate
                            .getForObject(String.format("http://localhost:8081/api/movie_info/%s", rating.getMovieId()), Movie.class);

                    if (movie != null) {
                        return new MovieItem(movie.getName(), movie.getDescription(), rating.getCount());
                    }
                    throw new IllegalArgumentException();
                })
                .collect(Collectors.toList());
    }

}
