package filmorateapp.controller;

import filmorateapp.model.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import filmorateapp.model.Film;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import filmorateapp.service.ValidationService;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private List<Film> films = new ArrayList<>();
    private int nextId = 0;
    @Autowired
    private ValidationService validationService;

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        try {
            validationService.validate(film);
            film.setId(nextId++);
            films.add(film);
            log.info("Фильм добавлен с Айди " + film.getId());
        } catch (Exception e) {
            log.error("Ошибка добавления фильма " + e.getMessage());
        }
        return film;
    }

    @PutMapping
    public Film updateFilm(@PathVariable int id, @RequestBody Film film) {
        try {
            for (int i = 0; i < films.size(); i++) {
                if (films.get(i).getId() == id) {
                    validationService.validate(film);
                    films.set(i, film);
                    log.info("Фильм с Айди" + id + " обновлён");
                    return film;
                }
            }
        } catch (Exception e) {
            log.error("Ошибка обновления фильма " + e.getMessage());
        }
        return film;
    }

    @GetMapping
    public List<Film> getAllFilms() {
        try {
            if (!films.isEmpty()) {// валидация в отдельном методе
                log.info("Получите Список фильмов" + films);
                return films;
            }
        } catch (Exception e) {
            log.error("Мапа пуста " + e.getMessage());
        }
        return films;
    }

    @PutMapping("/{id}/like/{userId}")
    public ResponseEntity<String> addLike(@PathVariable int id, @PathVariable int userId) {
        return null;
    }

    @DeleteMapping("/{id}/like/{userId}")
    public ResponseEntity<String> removeLike(@PathVariable int id, @PathVariable int userId) {
        return null;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Film>> getPopularFilms(@RequestParam(required = false, defaultValue = "10") int count) {
        return null;
    }

    @ControllerAdvice
    public class RestExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(ValidationException.class)
        public ResponseEntity<String> handleValidationException(ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleNotFoundException(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleInternalServerError(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}