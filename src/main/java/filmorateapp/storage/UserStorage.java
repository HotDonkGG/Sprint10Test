package filmorateapp.storage;

import filmorateapp.model.User;

public interface UserStorage {
    User add(User user);
    User update(int id, User user);
    User getById(int id);
    boolean delete(int id);
}