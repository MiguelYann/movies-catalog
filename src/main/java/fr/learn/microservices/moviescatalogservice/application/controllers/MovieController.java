package fr.learn.microservices.moviescatalogservice.application.controllers;

import fr.learn.microservices.moviescatalogservice.domain.models.MovieItem;
import fr.learn.microservices.moviescatalogservice.infrastructure.models.Movie;
import fr.learn.microservices.moviescatalogservice.infrastructure.models.Rating;
import fr.learn.microservices.moviescatalogservice.infrastructure.models.ResultRating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/movies")
public class MovieController {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{userId}")
    public List<MovieItem> getMovies(@PathVariable("userId") String userId) {

        log.info("Call rating API for retrieve ratings of {} user ", userId);

        ResultRating result = null;
        try {
            result = restTemplate.getForObject("http://localhost:8083/api/ratings/users/" + userId, ResultRating.class);
        } catch (Exception e) {
            log.error("Error when call API rating with message" + e);
        }

        log.info("Call Service infos, to retrieved information about movie with ratings {}", result);

        if (result != null)
            return result.getRatings().stream()
                    .map(rating -> {
                        Movie movie = restTemplate
                                .getForObject(String.format("http://localhost:8081/api/movie_info/%s", rating.getMovieId()), Movie.class);

                        if (movie != null) {
                            return new MovieItem(movie.getName(), movie.getDescription(), rating.getCount());
                        }
                        throw new IllegalArgumentException();
                    })
                    .collect(Collectors.toList());

        return Collections.emptyList();
    }

}
