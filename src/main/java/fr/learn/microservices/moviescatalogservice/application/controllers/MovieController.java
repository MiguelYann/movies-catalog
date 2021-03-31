package fr.learn.microservices.moviescatalogservice.application.controllers;

import fr.learn.microservices.moviescatalogservice.domain.models.MovieItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/movies")
public class MovieController {

    private final List<MovieItem> movieItems = List.of(new MovieItem(
            "If only", "Best movie", "5"));


    @GetMapping("/{userId}")
    public List<MovieItem> getMovies(@PathVariable("userId") String userId) {

        log.info("Id of user is {}", userId);

        return movieItems;
    }

}
