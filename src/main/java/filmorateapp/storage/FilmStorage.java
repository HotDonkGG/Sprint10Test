package filmorateapp.storage;

import filmorateapp.model.Film;
import filmorateapp.model.User;

import java.util.Arrays;

public interface FilmStorage {
    Film add(Film film);
    Film update(int id, Film film);
    Film getById(int id);
    boolean delete(int id);
}