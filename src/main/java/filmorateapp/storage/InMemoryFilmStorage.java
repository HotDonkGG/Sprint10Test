package filmorateapp.storage;

import filmorateapp.model.Film;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final List<Film> films = new ArrayList<>();
    private int nextId = 0;

    @Override
    public Film add(Film film) {
        film.setId(nextId++);
        films.add(film);
        return film;
    }

    @Override
    public Film update(int id, Film film) {
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getId() == id) {
                films.set(i, film);
                return film;
            }
        }
        return null;
    }

    @Override
    public Film getById(int id) {
        for (Film film : films) {
            if (film.getId() == id) {
                return film;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        for (int i = 0; i < films.size(); i++) {
            if(films.get(i).getId() == id){
                films.remove(i);
                return true;
            }
        }
        return false;
    }
}