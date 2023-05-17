package filmorateapp.service;

import filmorateapp.model.Film;
import filmorateapp.storage.FilmStorage;
import filmorateapp.storage.InMemoryFilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public void addLike(Film film) {
        int likes = film.getLikes();
        film.setLikes(likes + 1);
    }

    public void removeLike(Film film) {
        int likes = film.getLikes();
        if (likes > 0) {
            film.setLikes(likes - 1);
        }
    }
//    public List<Film> getBestFilms(){}

}
